package net.ramixin.mixson;

import org.quiltmc.loader.api.ModContainer;

public interface MixsonInitializer {

	String ENTRYPOINT_KEY = "init";

	void onInitialize(ModContainer modContainer);
}
