package com.metal.kamasutra;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Kamasutra {
    private String defaultAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private boolean useUppercase;

    public Kamasutra() {
        genAlphabet();
    }
    public Kamasutra(boolean useUppercase) {
        genAlphabet();
        this.useUppercase = useUppercase;
    }

    public Kamasutra(String alphabet) throws Exception {
        setDefaultAlphabet(alphabet);
    }

    public Kamasutra(String alphabet, boolean useUppercase) throws Exception {
        setDefaultAlphabet(alphabet);
        this.useUppercase = useUppercase;
    }

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

    public String getDefaultAlphabet() {
        return defaultAlphabet;
    }

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

    public boolean isUseUppercase() {
        return useUppercase;
    }

    public void setUseUppercase(boolean useUppercase) {
        this.useUppercase = useUppercase;
    }
}
