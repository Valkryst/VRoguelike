package com.valkryst.VRoguelike.item.builder;

import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VRoguelike.item.Item;
import com.valkryst.VTerminal.font.FontLoader;
import com.valkryst.VTerminal.misc.JSONFunctions;
import lombok.Data;
import lombok.NonNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ItemBuilder {
    /** The sprite. */
    private Sprite sprite;

    /** The name. */
    private String name;
    /** The description.. */
    private String description;

    /** The health. */
    private int stat_health;
    /** The strength. */
    private int stat_strength;
    /** The defense. */
    private int stat_defense;
    /** The accuracy (Percent chance to land an attack). */
    private int stat_accuracy;
    /** The dodge (Percent chance to dodge an attack). */
    private int stat_dodge;

    /** Constructs a new ItemBuilder. */
    public ItemBuilder() {
        reset();
    }

    /** Constructs an Item. */
    public Item build() {
        checkState();
        return new Item(this);
    }

    /** Ensures the builder is in a valid state. */
    protected void checkState() {
        if (sprite == null) {
            sprite = Sprite.UNKNOWN;
        }

        if (name == null || name.isEmpty()) {
            name = "Item";
        }

        if (description == null || description.isEmpty()) {
            description = "This is an unnamed Item.";
        }
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        sprite = Sprite.UNKNOWN;

        name = "Item";
        description = "This is an unnamed item.";

        stat_health = 0;
        stat_strength = 0;
        stat_defense = 0;
        stat_accuracy = 0;
        stat_dodge = 0;
    }

    /**
     * Loads a portion of the builder's data from JSON.
     *
     * @param jsonFilePath
     *        The path to the JSON file.
     *
     * @throws org.json.simple.parser.ParseException
     *         If there's an error when parsing the JSON.
     *
     * @throws IOException
     *         If an IO error occurs.
     *
     *         If the file does not exist, is a directory rather
     *         than a regular file, or for some other reason cannot
     *         be opened for reading.
     *
     * @throws NullPointerException
     *        If the file path is null.
     */
    public void loadFromJSON(final @NonNull String jsonFilePath) throws ParseException, IOException {
        if (jsonFilePath.isEmpty()) {
            throw new IllegalArgumentException("The JSON file path cannot be empty.");
        }

        loadFromJSON(new FileInputStream(jsonFilePath));
    }

    /**
     * Loads a portion of the builder's data from a JSON file within the Jar.
     *
     * @param jsonFilePath
     *         The path to the JSON file.
     *
     * @throws ParseException
     *         If there's an error when parsing the JSON.
     *
     * @throws IOException
     *         If an IO error occurs.
     *
     * @throws NullPointerException
     *        If the file path is null.
     */
    public void loadFromJSONInJar(final @NonNull String jsonFilePath) throws ParseException, IOException {
        if (jsonFilePath.isEmpty()) {
            throw new IllegalArgumentException("The JSON file path cannot be empty.");
        }

        final ClassLoader classLoader = FontLoader.class.getClassLoader();
        final InputStream jsonFileStream = classLoader.getResourceAsStream(jsonFilePath);

        loadFromJSON(jsonFileStream);

        jsonFileStream.close();
    }

    /**
     * Loads a portion of the builder's data from JSON.
     *
     * @param jsonFileStream
     *        The JSON input stream.
     *
     * @throws ParseException
     *         If there's an error when parsing the JSON.
     *
     * @throws IOException
     *         If an IO error occurs.
     *
     * @throws NullPointerException
     *        If the file stream is null.
     */
    public void loadFromJSON(final @NonNull InputStream jsonFileStream) throws ParseException, IOException {
        final InputStreamReader isr = new InputStreamReader(jsonFileStream, StandardCharsets.UTF_8);
        final BufferedReader br = new BufferedReader(isr);
        final List<String> lines = br.lines().collect(Collectors.toList());

        parseJSON(String.join("\n", lines));

        br.close();
        isr.close();
    }

    /**
     * Loads a portion of the builder's data from JSON.
     *
     * @param jsonData
     *        The JSON.
     *
     * @throws ParseException
     *         If there's an error when parsing the JSON.
     *
     * @throws NullPointerException
     *        If the data is null.
     */
    public void parseJSON(@NonNull String jsonData) throws ParseException {
        // Remove comments from JSON:
        jsonData = jsonData.replaceAll("/\\*.*\\*/", ""); // Strip '/**/' comments
        jsonData = jsonData.replaceAll("//.*(?=\\n)", ""); // Strip '//' comments

        final JSONParser parser = new JSONParser();
        final Object object = parser.parse(jsonData);

        parseJSON((JSONObject) object);
    }

    /**
     * Loads a portion of the builder's data from JSON.
     *
     * @param jsonObject
     *        The JSON.
     *
     * @throws NullPointerException
     *        If the object is null.
     */
    public void parseJSON(final @NonNull JSONObject jsonObject) {
        reset();

        final Sprite sprite = Sprite.valueOf((String) jsonObject.get("sprite"));

        final String name = (String) jsonObject.get("name");
        final String description = (String) jsonObject.get("description");

        final Integer stat_health = JSONFunctions.getIntElement(jsonObject, "stat_health");
        final Integer stat_strength = JSONFunctions.getIntElement(jsonObject, "stat_strength");
        final Integer stat_defense = JSONFunctions.getIntElement(jsonObject, "stat_defense");
        final Integer stat_accuracy = JSONFunctions.getIntElement(jsonObject, "stat_accuracy");
        final Integer stat_dodge = JSONFunctions.getIntElement(jsonObject, "stat_dodge");

        if (sprite != null) {
            this.sprite = sprite;
        }


        if (name != null && name.isEmpty() == false) {
            this.name = name;
        }

        if (description != null && description.isEmpty() == false) {
            this.description = description;
        }


        if (stat_health != null) {
            this.stat_health = stat_health;
        }

        if (stat_strength != null) {
            this.stat_strength = stat_strength;
        }

        if (stat_defense != null) {
            this.stat_defense = stat_defense;
        }

        if (stat_accuracy != null) {
            this.stat_accuracy = stat_accuracy;
        }

        if (stat_dodge != null) {
            this.stat_dodge = stat_dodge;
        }
    }
}
