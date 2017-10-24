package com.valkryst.VRoguelike.entity.builder;

import com.valkryst.VRoguelike.entity.Player;
import com.valkryst.VRoguelike.enums.Sprite;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.json.simple.JSONObject;

@Data
@EqualsAndHashCode(callSuper=true)
public class PlayerBuilder extends CreatureBuilder {
    public Player build() {
        super.checkState();

        if (super.getName().equals("Player")) {
            super.setName(super.getRace().generateName(super.getGender()));
        }

        return new Player(this);
    }

    @Override
    public void reset() {
        super.reset();
        super.setName("Player");
        super.setDescription("This is you.");
        super.setSprite(Sprite.PLAYER);
    }

    @Override
    public void parse(final @NonNull JSONObject jsonObject) {
        super.parse(jsonObject);
        this.checkType(jsonObject, "entity_player");
    }
}
