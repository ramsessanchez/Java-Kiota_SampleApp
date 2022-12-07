package TestingApp;

import com.azure.identity.DeviceCodeCredential;
import com.azure.identity.DeviceCodeCredentialBuilder;
import com.microsoft.kiota.authentication.AzureIdentityAccessTokenProvider;
import com.microsoft.serviceClient.GraphServiceClient;

public class GraphSignIn {

    private static GraphServiceClient graphClient = null;
    private static AzureIdentityAccessTokenProvider authProvider = null;

    public static void initializeGraphAuth(String appId, String... scopes ) {
        final DeviceCodeCredential credential = new DeviceCodeCredentialBuilder()
                .clientId(appId)
                .challengeConsumer(challenge -> System.out.println(challenge.getMessage()))
                .build();
        graphClient = new GraphServiceClient(credential, scopes);
    }


    public static void getUser() {
        graphClient.me().get().thenAccept(me -> {
            System.out.printf("Hello %s, your ID is %s%n", me.getDisplayName(), me.getId());
        }).exceptionally(err -> {
            System.out.printf("Error, %s%n", err.getMessage());
            return null;
        }).join();
    }

    public static void getUsers() {

        graphClient.users().get().thenAccept(users -> {
            System.out.printf("Found users: %s", users.getValue().size());
        }).exceptionally(err -> {
            System.out.println("Error: " + err.getMessage());
            return null;
        }).join();
    }



}
