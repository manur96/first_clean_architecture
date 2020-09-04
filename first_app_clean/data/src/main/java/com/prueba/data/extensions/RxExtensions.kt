package com.prueba.data.extensions

import io.reactivex.*
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper
import io.realm.Realm
import io.realm.RealmObject


fun <T> Maybe<T>.save(saveFunction: (T) -> Unit): Maybe<T> {
    return MaybeSave(this, saveFunction)
}

private class MaybeSave<T>(val source: MaybeSource<T>, val saveFunction: (T) -> Unit) : Maybe<T>() {

    override fun subscribeActual(observer: MaybeObserver<in T>) {
        source.subscribe(MaybeSaveObserver<T>(observer, saveFunction))
    }

    internal class MaybeSaveObserver<T>(val downstream: MaybeObserver<in T>, val saveFunction: (T) -> Unit) : MaybeObserver<T>, Disposable {

        private var upstream: Disposable? = null

        override fun dispose() {
            val d = this.upstream
            this.upstream = DisposableHelper.DISPOSED
            d?.dispose()
        }

        override fun isDisposed(): Boolean = upstream?.isDisposed ?: false

        override fun onSubscribe(d: Disposable) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d

                downstream.onSubscribe(this)
            }
        }

        override fun onSuccess(value: T) {
            saveFunction(value)
            downstream.onSuccess(value)
        }

        override fun onError(e: Throwable) = downstream.onError(e)

        override fun onComplete() = downstream.onComplete()
    }
}

fun <T> Single<T>.save(saveFunction: (T) -> Unit): Single<T> {
    return SingleSave(this, saveFunction)
}

private class SingleSave<T>(val source: SingleSource<T>, val saveFunction: (T) -> Unit) : Single<T>() {

    override fun subscribeActual(observer: SingleObserver<in T>) {
        source.subscribe(SingleSaveObserver<T>(observer, saveFunction))
    }

    internal class SingleSaveObserver<T>(val downstream: SingleObserver<in T>, val saveFunction: (T) -> Unit) : SingleObserver<T>, Disposable {

        private var upstream: Disposable? = null

        override fun dispose() {
            val d = this.upstream
            this.upstream = DisposableHelper.DISPOSED
            d?.dispose()
        }

        override fun isDisposed(): Boolean = upstream?.isDisposed ?: false

        override fun onSubscribe(d: Disposable) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d

                downstream.onSubscribe(this)
            }
        }

        override fun onSuccess(value: T) {
            saveFunction(value)
            downstream.onSuccess(value)
        }

        override fun onError(e: Throwable) = downstream.onError(e)
    }
}

/**
 * Realm
 * */
fun RealmObject.save() {
    val realm = Realm.getDefaultInstance()
    realm.use {
        it.executeTransaction {
            it.copyToRealmOrUpdate(this)
        }
    }
}

fun <T : RealmObject> List<T>.save() {
    val realm = Realm.getDefaultInstance()
    realm.use {
        it.executeTransaction {
            it.copyToRealmOrUpdate(this)
        }
    }
}

fun <T : RealmObject> removeAll(realmObject: Class<T>) {
    val realm = Realm.getDefaultInstance()
    realm.use {
        it.executeTransaction {
            it.where(realmObject).findAll().deleteAllFromRealm()
        }
    }
}