package main.java.assets;

import main.java.registry.Registry;

public class AssetManager {

    public static Texture getTexture(String name) {
        return Registry.TEXTURE_REGISTRY.get(name);
    }
}
