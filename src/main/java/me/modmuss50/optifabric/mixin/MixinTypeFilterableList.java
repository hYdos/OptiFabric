package me.modmuss50.optifabric.mixin;

import com.google.common.collect.Iterators;
import net.minecraft.util.TypeFilterableList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Mixin(TypeFilterableList.class)
public abstract class MixinTypeFilterableList<T> {


    @Shadow @Final private Map<Class<?>, List<T>> elementsByType;

    @Shadow protected abstract Class<?> method_6407(Class<?> var1);

    @Shadow @Final private List<T> allElements;

    /**
     * @author hydos
     * @reason optifabric for 1.8.9
     */
    @Overwrite
    public <S> Iterable<S> method_6408(final Class<S> var1) {
        return () -> {
            List<T> list = elementsByType.get(method_6407(var1));
            if (list == null) {
                return Collections.emptyIterator();
            } else {
                Iterator<T> iterator = list.iterator();
                return Iterators.filter(iterator, var1);
            }
        };
    }

    /**
     * @author hydos
     * @reason optifabric for 1.8.9
     */
    @Overwrite
    public Iterator<T> iterator() {
        return allElements.isEmpty() ? Collections.emptyIterator() : Iterators.unmodifiableIterator(allElements.iterator());
    }

}
