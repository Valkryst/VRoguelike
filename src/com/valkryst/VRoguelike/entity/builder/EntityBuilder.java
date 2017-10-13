package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Entity;
import com.valkryst.VRoguelike.enums.Sprite;
import com.valkryst.VTerminal.font.FontLoader;
import com.valkryst.VTerminal.misc.JSONFunctions;
import lombok.Data;
import lombok.NonNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Point;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class EntityBuilder {
    /** The name. */
    private String name;
    /** The description. */
    private String description;
    /** The x/y-axis coordinates. */
    private Point position = new Point(-1, -1);
    /** The sprite. */
    private Sprite sprite;

    /** Constructs a new EntityBuilder. */
    public EntityBuilder() {
        reset();
    }

    /** Constructs an Entity. */
    public Entity build() {
        checkState();
        return new Entity(this);
    }

    /**
     * Ensures the builder is in a valid state.
     *
     * @throws NullPointerException
     *        If the name or description are null.
     *
     * @throws IllegalArgumentException
     *        If the x/y positions are below zero.
     */
    protected void checkState() {
        if (name == null || name.isEmpty()) {
            name = "Entity";
        }

        if (description == null || description.isEmpty()) {
            description = "This is an unnamed Entity.";
        }

        if (position.x < 0) {
            throw new IllegalArgumentException("The x value (" + position.x + ") cannot be less than zero.");
        }

        if (position.y < 0) {
            throw new IllegalArgumentException("The y value (" + position.y + ") cannot be less than zero.");
        }

        Objects.requireNonNull(sprite);
    }

    /** Resets the builder to it's default state. */
    public void reset() {
        name = "";
        description = "";
        position.setLocation(-1, -1);
        sprite = null;
    }

    public void setX(final int x) {
        if (x < 0) {
            throw new IllegalArgumentException("The x value (" + position.x + ") cannot be less than zero.");
        }

        position.setLocation(x, position.y);
    }

    public void setY(final int y) {
        if (y < 0) {
            throw new IllegalArgumentException("The y value (" + position.y + ") cannot be less than zero.");
        }

        position.setLocation(position.x, y);
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

        final String name = (String) jsonObject.get("name");
        final String description = (String) jsonObject.get("description");

        final Integer x = JSONFunctions.getIntElement(jsonObject, "x");
        final Integer y = JSONFunctions.getIntElement(jsonObject, "y");

        final Sprite sprite = Sprite.valueOf((String) jsonObject.get("sprite"));


        if (name != null && name.isEmpty() == false) {
            this.name = name;
        }

        if (description != null && description.isEmpty() == false) {
            this.description = description;
        }



        if (x != null && y != null) {
            this.position = new Point(x, y);
        }



        if (sprite != null) {
            this.sprite = sprite;
        }
    }
}
