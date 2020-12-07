package me.modmuss50.optifabric.mixin;

import me.modmuss50.optifabric.mod.Optifabric;
import me.modmuss50.optifabric.mod.OptifabricError;
import me.modmuss50.optifabric.mod.OptifineVersion;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen {

	protected MixinTitleScreen() {
		super();
	}

	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo info) {
		Optifabric.checkForErrors();
	}

	@Inject(method = "render", at = @At("TAIL"))
	private void render(CallbackInfo info) {
		if (!OptifabricError.hasError()) {
			this.textRenderer.draw(OptifineVersion.version, 2, this.height - 20, 0xFFFFFFFF);
		}
	}
}
