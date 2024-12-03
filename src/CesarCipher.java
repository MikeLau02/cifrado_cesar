public class CesarCipher {
    private static final String ALFABETO = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;':,./<>? ";

    public String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            int index = ALFABETO.indexOf(character);
            if (index != -1) {
                int newIndex = (index + shift) % ALFABETO.length();
                encryptedText.append(ALFABETO.charAt(newIndex));
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            int index = ALFABETO.indexOf(character);
            if (index != -1) {
                int newIndex = (index - shift + ALFABETO.length()) % ALFABETO.length();
                decryptedText.append(ALFABETO.charAt(newIndex));
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }
}
