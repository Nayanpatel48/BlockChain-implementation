import java.security.*;
import java.util.Scanner;

public class Crypto
{
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Scanner sc = new Scanner(System.in);

        KeyPairGenerator keyPairGen1 = KeyPairGenerator.getInstance("DSA");
        keyPairGen1.initialize(2048);
        // Key size in bits
        //key pair generated
        KeyPair keyPair1 = keyPairGen1.generateKeyPair();//a

        KeyPairGenerator keyPairGen2 = KeyPairGenerator.getInstance("DSA");
        keyPairGen2.initialize(2048);
        // Key size in bits
        //key pair generated
        KeyPair keyPair2 = keyPairGen2.generateKeyPair();//b

        //fetched privateKey and publicKey from keyPair
        PrivateKey privateKey1 = keyPair1.getPrivate();
        PublicKey publicKey1 = keyPair1.getPublic();

        PrivateKey privateKey2 = keyPair2.getPrivate();
        PublicKey publicKey2 = keyPair2.getPublic();

        System.out.println(publicKey1);
        System.out.println(publicKey2);
        // Don't print the private key directly (security concern)
        // System.out.println("Private key:" + privateKey);

        // You can use the private key for signing purposes

        //taking input message
        System.out.println("Enter message:");
        String message = sc.nextLine();
        //this one will be false because we have used a's private key to access the b's message
        System.out.println(signatureVerification(message, privateKey1, publicKey2));
    }
    public static boolean signatureVerification(
            String message,
            PrivateKey privateKey,
            PublicKey publicKey
    ) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");

        //Initializing the signature
        sign.initSign(privateKey);
        byte[] bytes = "Hello how are you".getBytes();

        //Adding data to the signature
        sign.update(bytes);

        //Calculating the signature
        byte[] signature = sign.sign();

        //Initializing the signature
        sign.initVerify(publicKey);
        sign.update(bytes);

        //Verifying the signature
        return sign.verify(signature);
    }
}