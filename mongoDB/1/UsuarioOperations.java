package app;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

public class UsuarioOperations {
    public static void main(String[] args) {
        // Conecta ao MongoDB (URI padrão localhost:27017)
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase database = mongoClient.getDatabase("test"); // ajuste o nome do DB se necessário
            MongoCollection<Document> collection = database.getCollection("usuarios");

            // Insere 3 registros usando insertMany
            List<Document> usuarios = Arrays.asList(
                    new Usuario("Alice", 25).toDocument(),
                    new Usuario("Bob", 30).toDocument(),
                    new Usuario("Charlie", 35).toDocument()
            );
            collection.insertMany(usuarios);
            System.out.println("Após insertMany:");
            printAll(collection);

            // Atualiza a idade de Bob para 32
            collection.updateOne(Filters.eq("name", "Bob"), Updates.set("age", 32));
            System.out.println("\nApós atualizar Bob para 32:");
            printAll(collection);

            // Remove Charlie
            collection.deleteOne(Filters.eq("name", "Charlie"));
            System.out.println("\nApós remover Charlie:");
            printAll(collection);
        }
    }

    private static void printAll(MongoCollection<Document> collection) {
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }
    }
}
