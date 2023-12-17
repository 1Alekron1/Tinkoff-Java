package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask2 {

    @Test
    @DisplayName("При изменении операции на умножение, результат должен быть правильным")
    void whenChangingOperation_thenResultShouldBeCorrect() throws IllegalAccessException, InstantiationException {
        ByteBuddyAgent.install();
        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();
        new ByteBuddy().redefine(ArithmeticUtilsReload.class).name(ArithmeticUtils.class.getName()).make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        assertEquals(arithmeticUtils.sum(3, 3), 9);
    }

}
