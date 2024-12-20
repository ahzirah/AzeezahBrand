package com.example.azeezahbrand.domain.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class ShoppingRepository {

    val firestore = FirebaseFirestore.getInstance();

    suspend inline fun <reified T: Any> saveDocument(
        collectionName: String,
        data: T,
        documentId: String
    ): Boolean {

        return try {

            firestore.collection(collectionName)
                .document(documentId)
                .set(data)
                .await()
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }
    }


    suspend inline fun <reified T: Any> saveManyDoc(
        collectionName: String,
        items: List<Pair<String, T>>
    ): Boolean{

        return try {
            val batch = firestore.batch()

            items.forEach { (id, data) ->
                val documentRef = firestore.collection(collectionName).document(id)
                batch.set(documentRef, data)
            }

            batch.commit()
            true

        }catch (e: Exception){
            e.printStackTrace()
            false
        }
    }


    suspend inline fun <reified T: Any> fetchDocuments(
        collection: String,
        queryParams: Pair<String, Any>
    ): List<T>
    {
        return try {
            val (fieldKey, fieldValue) = queryParams

            val snapshot: QuerySnapshot = firestore.collection(collection)
                .whereEqualTo(fieldKey, fieldValue)
                .limit(50).get().await()
println(snapshot.documents.mapNotNull { resultItems -> resultItems.toObject<T>()  })
         return   snapshot.documents.mapNotNull { resultItems -> resultItems.toObject<T>()  }


//            emptyList()
        }catch (e: Exception){
            e.printStackTrace()
            emptyList()
        }
    }

    suspend  fun  delete( collectionName: String,
                                                docId: String):Boolean{
        return try {

            firestore.collection(collectionName)
                .document(docId)
                .delete()
                .await()
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }

    }

    suspend  fun  deleteMany( collectionName: String,
                              queryParams: Pair<String, Any>):Boolean{
        return try {
            val (fieldKey, fieldValue) = queryParams
            firestore.collection(collectionName).whereEqualTo(fieldKey, fieldValue).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        document.reference.delete()
                    }
                }
            true
        } catch (e: Exception){
            e.printStackTrace()
            false
        }

    }
}