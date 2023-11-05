package fobot_2.cmath;

import org.jetbrains.annotations.NotNull;

public interface Vector<T>
{
    @NotNull String toString();
    float getDistance(@NotNull T pos);
    @NotNull T add(@NotNull T vec);
    @NotNull T sub(@NotNull T vec);
    @NotNull T subr(@NotNull T vec);
    @NotNull T mult(@NotNull T vec);
    @NotNull T div(@NotNull T vec);
    @NotNull T divr(@NotNull T vec);

    @NotNull T add(int value);
    @NotNull T sub(int value);
    @NotNull T subr(int value);
    @NotNull T mult(int value);
    @NotNull T div(int value);
    @NotNull T divr(int value);

    @NotNull T add(float value);
    @NotNull T sub(float value);
    @NotNull T subr(float value);
    @NotNull T mult(float value);
    @NotNull T div(float value);
    @NotNull T divr(float value);
}
