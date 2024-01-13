package ricardo.porfirio.ApiProducts.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import ricardo.porfirio.ApiProducts.Models.ProductsModel;

@Service
public class ProductsService {

    @Autowired
    private Firestore dbFIFirestore;

    private int paginationSize = 2;

    private CollectionReference products;

    private Query firstPage;

    private ApiFuture<QuerySnapshot> future;

    private List<QueryDocumentSnapshot> docs;

    public ProductsService(Firestore dbFIFirestore) {
        this.dbFIFirestore = dbFIFirestore;
        this.products = dbFIFirestore.collection("domain").document("ApiProducts").collection("id");
        this.firstPage = products.orderBy("id").limit(paginationSize);
        this.future = firstPage.get();
    }

    public long getlastId() throws InterruptedException, ExecutionException {

        CollectionReference collection = dbFIFirestore.collection("domain")
        .document("ApiProducts")
        .collection("id");

        Query snapshot = collection.orderBy("id", Direction.DESCENDING).limit(1);

        ApiFuture<QuerySnapshot> querySnapshot = snapshot.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        
        long id = Long.parseLong(documents.get(0).getId());

        collection.orderBy("id", Direction.DESCENDING).get();

        return id + 1;
    }

    public String saveProduct(ProductsModel product) throws InterruptedException, ExecutionException{

        long id = this.getlastId();

        product.setId(id);

        ApiFuture<WriteResult> collectionApiFuture = dbFIFirestore.collection("domain")
        .document("ApiProducts")
        .collection("id")
        .document(String.valueOf(id))
        .set(product);

        return collectionApiFuture.get().getUpdateTime().toString();  
        
    }

    public ProductsModel getProduct(String productId) throws InterruptedException, ExecutionException{
        DocumentReference documentReference = dbFIFirestore.collection("domain")
        .document("ApiProducts")
        .collection("id")
        .document(productId);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        ProductsModel crud;
        if(document.exists()){
            crud = document.toObject(ProductsModel.class);
            
            return crud;
        }
        return null;
    }

    public String updateProduct(String id, ProductsModel crud) throws InterruptedException, ExecutionException {

        DocumentReference cApiFuture = dbFIFirestore.collection("domain")
        .document("ApiProducts")
        .collection("id")
        .document(id);

        Map<String, Object> updateFields = new HashMap<>();

        if (crud.getName() != null) {
            updateFields.put("name", crud.getName());
        }
        if (crud.getDescription() != null) {
            updateFields.put("description", crud.getDescription());
        }
        if (crud.getPrice() != null) {
            updateFields.put("price", crud.getPrice());
        }

        ApiFuture<WriteResult> writeResult = cApiFuture.update(updateFields);

        WriteResult result = writeResult.get();
        
        return result.getUpdateTime().toString();  
    }

    public ResponseEntity<List<ProductsModel>> getAllProducts() throws InterruptedException, ExecutionException{
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = dbFIFirestore.collection("domain").document("ApiProducts")
        .collection("id").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        return ResponseEntity.ok(
            documents
                .stream()
                .map(
                    document -> document.toObject(ProductsModel.class)
                )
                .collect(Collectors.toList())
        );
    }

    public ResponseEntity<List<ProductsModel>> getFirstPageProducts() throws InterruptedException, ExecutionException, TimeoutException{

        List<QueryDocumentSnapshot> docs = future.get(30, TimeUnit.SECONDS).getDocuments();

        return ResponseEntity.ok(
            docs
                .stream()
                .map(
                    document -> document.toObject(ProductsModel.class)
                )
                .collect(Collectors.toList())
        );

    }

    public ResponseEntity<List<ProductsModel>> getNextPageProducts() throws InterruptedException, ExecutionException, TimeoutException{

        // Construct query for the next 25 products.
        QueryDocumentSnapshot lastDoc = docs.get(docs.size() - 1);
        Query secondPage = products.orderBy("id").startAfter(lastDoc).limit(paginationSize);

        future = secondPage.get();
        docs = future.get(30, TimeUnit.SECONDS).getDocuments();

        return ResponseEntity.ok(
            docs
                .stream()
                .map(
                    document -> document.toObject(ProductsModel.class)
                )
                .collect(Collectors.toList())
        );

    }
}
