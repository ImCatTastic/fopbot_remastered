package fobot_2.cmath;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vec4 implements Vector<Vec4>
{
    public int x;
    public int y;
    public int z;
    public int w;
    public Vec4(int x, int y, int z, int w)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    public Vec4(@NotNull Vec4 vec4)
    {
        this.x = vec4.x;
        this.y = vec4.y;
        this.z = vec4.z;
        this.w = vec4.z;
    }

    public Vec4(@NotNull Vec3 vec3, int value)
    {
        this.x = vec3.x;
        this.y = vec3.y;
        this.z = vec3.z;
        this.w = value;
    }

    public Vec3 getXYZ()
    {
        return new Vec3(x, y, z);
    }

    public Vec2 getXY()
    {
        return new Vec2(x, y);
    }

    public @NotNull String toString()
    {
        return "(" + x + "|" + y + "|" + z + "|" + w +")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vec4 other = (Vec4) obj;

        return this.x == other.x &&
            this.y == other.y &&
            this.z == other.z &&
            this.w == other.w;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z, w);
    }

    public float getDistance(@NotNull Vec4 pos)
    {
        int deltaX = pos.x - x;
        int deltaY = pos.y - y;
        int deltaZ = pos.z - z;
        int deltaW = pos.w - w;

        return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ) + (deltaW * deltaW));
    }

    public @NotNull Vec4 add(@NotNull Vec4 vec)
    {
        int resX = this.x + vec.x;
        int resY = this.y + vec.y;
        int resZ = this.z + vec.z;
        int resW = this.w + vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 sub(@NotNull Vec4 vec)
    {
        int resX = this.x - vec.x;
        int resY = this.y - vec.y;
        int resZ = this.z - vec.z;
        int resW = this.w - vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 subr(@NotNull Vec4 vec)
    {
        int resX = vec.x - this.x;
        int resY = vec.y - this.y;
        int resZ = vec.z - this.z;
        int resW = vec.w - this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 mult(@NotNull Vec4 vec)
    {
        int resX = this.x * vec.x;
        int resY = this.y * vec.y;
        int resZ = this.z * vec.z;
        int resW = this.w * vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 div(@NotNull Vec4 vec)
    {
        int resX = this.x / vec.x;
        int resY = this.y / vec.y;
        int resZ = this.z / vec.z;
        int resW = this.w / vec.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    public @NotNull Vec4 divr(@NotNull Vec4 vec)
    {
        int resX = vec.x / this.x;
        int resY = vec.y / this.y;
        int resZ = vec.z / this.z;
        int resW = vec.w / this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 add(int value)
    {
        int resX = this.x + value;
        int resY = this.y + value;
        int resZ = this.z + value;
        int resW = this.w + value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 sub(int value)
    {
        int resX = this.x - value;
        int resY = this.y - value;
        int resZ = this.z - value;
        int resW = this.w - value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 subr(float value)
    {
        int resX = (int)(value - this.x);
        int resY = (int)(value - this.y);
        int resZ = (int)(value - this.z);
        int resW = (int)(value - this.w);
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 mult(int value)
    {
        int resX = this.x * value;
        int resY = this.y * value;
        int resZ = this.z * value;
        int resW = this.w * value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 div(int value)
    {
        int resX = this.x / value;
        int resY = this.y / value;
        int resZ = this.z / value;
        int resW = this.w / value;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 divr(int value)
    {
        int resX = value / this.x;
        int resY = value / this.y;
        int resZ = value / this.z;
        int resW = value / this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 add(float value)
    {
        int resX = (int)(this.x + value);
        int resY = (int)(this.y + value);
        int resZ = (int)(this.z + value);
        int resW = (int)(this.w + value);
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 sub(float value)
    {
        int resX = (int)(this.x - value);
        int resY = (int)(this.y - value);
        int resZ = (int)(this.z - value);
        int resW = (int)(this.w - value);
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 subr(int value)
    {
        int resX = value - this.x;
        int resY = value - this.y;
        int resZ = value - this.z;
        int resW = value - this.w;
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 mult(float value)
    {
        int resX = (int)(this.x * value);
        int resY = (int)(this.y * value);
        int resZ = (int)(this.z * value);
        int resW = (int)(this.w * value);
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 div(float value)
    {
        int resX = (int)(this.x / value);
        int resY = (int)(this.y / value);
        int resZ = (int)(this.z / value);
        int resW = (int)(this.w / value);
        return new Vec4(resX, resY, resZ, resW);
    }

    @Override
    public @NotNull Vec4 divr(float value)
    {
        int resX = (int)(value / this.x);
        int resY = (int)(value / this.y);
        int resZ = (int)(value / this.z);
        int resW = (int)(value / this.w);
        return new Vec4(resX, resY, resZ, resW);
    }
}
