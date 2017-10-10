package com.valkryst.VRoguelike.enums;

import com.valkryst.generator.MarkovNameGenerator;
import lombok.NonNull;

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
     *          The path to the training data file containing
     *          the male names.
     *
     * @param femaleNamesFile
     *          The path to the training data file containing
     *          the female names.
     *
     * @param lastNamesFile
     *          The path to the training data file containing
     *          the last names.
     *
     * @throws NullPointerException
     *          If the male, female, or last names is null.
     */
    Race(final @NonNull String maleNamesFile, final @NonNull String femaleNamesFile, final @NonNull String lastNamesFile) {
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
     *          The path to the training data file.
     *
     * @return
     *          A trained MarkovNameGenerator.
     *
     * @throws IOException
     *           If an I/O error occurs.
     *
     * @throws NullPointerException
     *          If the training data is null.
     */
    private MarkovNameGenerator trainNameGenerator(final @NonNull String trainingDataFile) throws IOException {
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

    /**
     * Generates a name with both a first and last
     * name.
     *
     * @param gender
     *          The gender to use when generating the
     *          first name.
     *
     * @return
     *          The name.
     */
    public String generateName(final @NonNull Gender gender) {
        final int firstNameLength = ThreadLocalRandom.current().nextInt(8) + 3;
        final int lastNameLength = ThreadLocalRandom.current().nextInt(10) + 3;

        return (gender == Gender.MALE ? maleNameGenerator : femaleNameGenerator).generateName(firstNameLength)
                + " " + lastNameGenerator.generateName(lastNameLength);
    }
}
