package net.ramixin.mixson.mixins;

import net.minecraft.core.registries.BuiltInRegistries;
import net.ramixin.mixson.MixsonInitializer;
import org.quiltmc.loader.api.entrypoint.EntrypointUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltInRegistries.class)
public class BuiltInRegistriesMixin {

	@Unique
	private static final Logger MIXSON_LOGGER = LoggerFactory.getLogger("Mixson Entrypoint");

	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void runEntrypoints(CallbackInfo ci) {
		MIXSON_LOGGER.info("Loading Mixson Entrypoints...");
		try {
			EntrypointUtil.invoke(MixsonInitializer.ENTRYPOINT_KEY, MixsonInitializer.class, MixsonInitializer::onInitialize);
		} catch (Exception e) {
			MIXSON_LOGGER.error("Early Mixson loading failed", e);
		}

	}

}
