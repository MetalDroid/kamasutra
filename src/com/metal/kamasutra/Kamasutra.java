package com.metal.kamasutra;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Kamasutra {
    private String defaultAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private boolean useUppercase;

    /**
     * Constructor using default alphabet (random).
     */
    public Kamasutra() {
        genAlphabet();
    }

    /**
     * Constructor using default alphabet (random), specify whether to use all uppercase.
     * @param useUppercase Specify whether to use all uppercase.
     */
    public Kamasutra(boolean useUppercase) {
        genAlphabet();
        this.useUppercase = useUppercase;
    }

    /**
     * Constructor using default alphabet (no randomize).
     * @param alphabet String that contains the alphabet to use.
     * @throws Exception The length of the alphabet must be a multiple of 2 and greater than 0.
     */
    public Kamasutra(String alphabet) throws Exception {
        setDefaultAlphabet(alphabet);
    }

    /**
     * Constructor using default alphabet (no randomize), specify whether to use all uppercase.
     * @param alphabet String that contains the alphabet to use.
     * @param useUppercase Specify whether to use all uppercase.
     * @throws Exception The length of the alphabet must be a multiple of 2 and greater than 0.
     */
    public Kamasutra(String alphabet, boolean useUppercase) throws Exception {
        setDefaultAlphabet(alphabet);
        this.useUppercase = useUppercase;
    }

    /**
     * Randomizes the default alphabet or the one provided.
     */
    public void genAlphabet(){
        char[] aTemp = defaultAlphabet.toCharArray();
        for (int i = aTemp.length - 1; i > 0; --i) {
            int j = new Random().nextInt(i + 1);
            char temp = aTemp[i];
            aTemp[i] = aTemp[j];
            aTemp[j] = temp;
        }
        defaultAlphabet = new String(aTemp).toUpperCase();
    }

    /**
     * Encrypt or decrypt a text.
     * @param input Text to encrypt or decrypt.
     * @return Encrypted or decrypted text (depending on input).
     */
    public String encDec(String input){
        String res = input.toUpperCase();
        char[] inputChars = res.toCharArray();
        StringBuilder output = new StringBuilder();
        for (char inputChar : inputChars) {
            if (inputChar == ' ' || !defaultAlphabet.contains(String.valueOf(inputChar))){
                output.append(' ');
            } else {
                output.append(defaultAlphabet.charAt((defaultAlphabet.indexOf(inputChar) + (defaultAlphabet.length() / 2)) % defaultAlphabet.length()));
            }
        }
        return isUseUppercase() ? output.toString().toUpperCase() : output.toString().toLowerCase();
    }

    /**
     * Get the currently used alphabet.
     * @return Currently used alphabet.
     */
    public String getDefaultAlphabet() {
        return defaultAlphabet;
    }

    /**
     * Assign the alphabet to use.
     * @param defaultAlphabet Custom alphabet.
     * @throws Exception The length of the alphabet must be a multiple of 2 and greater than 0.
     */
    public void setDefaultAlphabet(String defaultAlphabet) throws Exception {
        Set<Character> uniques = new HashSet<>();
        char[] c = defaultAlphabet.toCharArray();
        for (char value : c) {
            uniques.add(value);
        }
        if (defaultAlphabet.length() == 0) {
            genAlphabet();
        } else if (defaultAlphabet.length() % 2 != 0) {
            throw new Exception("Error: The length of the alphabet must be an even number");
        } else if (c.length > uniques.size()){
            throw new Exception("Error: There cannot be repeated characters in the alphabet.");
        } else {
            this.defaultAlphabet = defaultAlphabet.toUpperCase();
        }
    }

    /**
     * Check if everything should be converted to uppercase.
     * @return The state of the Boolean value.
     */
    public boolean isUseUppercase() {
        return useUppercase;
    }

    /**
     * Specifies whether to convert everything to uppercase.
     * If "false" is specified, everything will be converted to lowercase.
     * @param useUppercase Use uppercase or not.
     */
    public void setUseUppercase(boolean useUppercase) {
        this.useUppercase = useUppercase;
    }
}
