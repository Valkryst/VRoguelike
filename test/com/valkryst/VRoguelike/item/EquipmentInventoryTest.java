package com.valkryst.VRoguelike.item;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class EquipmentInventoryTest {
    private EquipmentInventory inventory;
    private final EquippableItem item = new EquippableItem("TestItem", "TestDescription", EquipmentSlot.HEAD);

    @Before
    public void initializeInventory() {
        inventory = new EquipmentInventory();
    }

    @Test
    public void testGetItemInSlot_withFullSlot() {
        inventory.setItemInSlot(EquipmentSlot.HEAD, item);

        final Optional<EquippableItem> optItem = inventory.getItemInSlot(EquipmentSlot.HEAD);
        Assert.assertTrue(optItem.isPresent());
    }

    @Test
    public void testGetItemInSlot_withEmptySlot() {
        final Optional<EquippableItem> optItem = inventory.getItemInSlot(EquipmentSlot.HEAD);
        Assert.assertFalse(optItem.isPresent());
    }

    @Test(expected=NullPointerException.class)
    public void testGetItemInSlot_withNullSlot() {
        inventory.getItemInSlot(null);
    }

    @Test
    public void testSetItemInSlot_withFullSlot() {
        final EquippableItem itemB = new EquippableItem("TestItem", "TestDescription", EquipmentSlot.HEAD);

        inventory.setItemInSlot(EquipmentSlot.HEAD, item);
        Optional<EquippableItem> optItem = inventory.getItemInSlot(EquipmentSlot.HEAD);
        Assert.assertTrue(optItem.isPresent());
        Assert.assertEquals(item, optItem.get());

        inventory.setItemInSlot(EquipmentSlot.HEAD, itemB);
        optItem = inventory.getItemInSlot(EquipmentSlot.HEAD);
        Assert.assertTrue(optItem.isPresent());
        Assert.assertEquals(itemB, optItem.get());
    }

    @Test
    public void testSetItemInSlot_withEmptySlot() {
        Optional<EquippableItem> optItem = inventory.getItemInSlot(EquipmentSlot.HEAD);
        Assert.assertFalse(optItem.isPresent());

        inventory.setItemInSlot(EquipmentSlot.HEAD, item);

        optItem = inventory.getItemInSlot(EquipmentSlot.HEAD);
        Assert.assertTrue(optItem.isPresent());
        Assert.assertEquals(item, optItem.get());
    }

    @Test(expected=NullPointerException.class)
    public void testSetItemInSlot_withNullSlot() {
        inventory.setItemInSlot(null, item);
    }
}
