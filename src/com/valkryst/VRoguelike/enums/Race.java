package com.valkryst.VRoguelike.enums;

import com.valkryst.generator.MarkovNameGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum Race {
    HUMAN("Human/Scottish_Gaelic/Male.txt", "Human/Scottish_Gaelic/Female.txt", "Human/Scottish_Gaelic/Last.txt");

    private MarkovNameGenerator maleNameGenerator;
    private MarkovNameGenerator femaleNameGenerator;
    private MarkovNameGenerator lastNameGenerator;

    /**
     * Constructs a new Race enum.
     *
     * @param maleNamesFile
     *        The path to the training data file containing
     *        the male names.
     *
     * @param femaleNamesFile
     *        The path to the training data file containing
     *        the female names.
     *
     * @param lastNamesFile
     *        The path to the training data file containing
     *        the last names.
     */
    Race(final String maleNamesFile, final String femaleNamesFile, final String lastNamesFile) {
        try {
            maleNameGenerator = trainNameGenerator(maleNamesFile);
            femaleNameGenerator = trainNameGenerator(femaleNamesFile);
            lastNameGenerator = trainNameGenerator(lastNamesFile);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructs and trains a MarkovNameGenerator using a
     * file of training data.
     *
     * @param trainingDataFile
     *        The path to the training data file.
     *
     * @return
     *        A trained MarkovNameGenerator.
     *
     * @throws IOException
     *         If an I/O error occurs.
     */
    private MarkovNameGenerator trainNameGenerator(final String trainingDataFile) throws IOException {
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream(trainingDataFile);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        final List<String> trainingNames = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            trainingNames.add(line);
        }

        return new MarkovNameGenerator(trainingNames);
    }

    public String generateMaleName() {
        final int firstNameLength = ThreadLocalRandom.current().nextInt(10) + 3;
        final int lastNameLength = ThreadLocalRandom.current().nextInt(12) + 3;

        return maleNameGenerator.generateName(firstNameLength)
               + lastNameGenerator.generateName(lastNameLength);
    }

    public String generateFemaleName() {
        final int firstNameLength = ThreadLocalRandom.current().nextInt(10) + 3;
        final int lastNameLength = ThreadLocalRandom.current().nextInt(12) + 3;

        return femaleNameGenerator.generateName(firstNameLength)
                + lastNameGenerator.generateName(lastNameLength);
    }
}
