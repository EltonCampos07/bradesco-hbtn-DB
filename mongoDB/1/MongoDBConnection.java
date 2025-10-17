import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection implements AutoCloseable {

    private static final String USERNAME = "aluno";
    private static final String PASSWORD = "BN10UNhMxMDZUgEh";
    private static final String CLUSTER_URL = "cluster0.brbyr.mongodb.net";
    private static final String DATABASE_NAME = "Cluster0";

    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        try {
            String connectionString = String.format(
                    "mongodb+srv://%s:%s@%s/?retryWrites=true&w=majority",
                    USERNAME, PASSWORD, CLUSTER_URL
            );

            ConnectionString connString = new ConnectionString(connectionString);
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .build();

            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase(DATABASE_NAME);

            System.out.println("Conexão estabelecida com sucesso ao MongoDB!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    @Override
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão encerrada com sucesso.");
        }
    }
}