package fobot_2.cmath;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vec3 implements Vector<Vec3>
{
    public int x;
    public int y;
    public int z;
    public Vec3(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec3(@NotNull Vec3 vec3)
    {
        this.x = vec3.x;
        this.y = vec3.y;
        this.z = vec3.z;
    }

    public Vec3(@NotNull Vec2 vec2, int value)
    {
        this.x = vec2.x;
        this.y = vec2.y;
        this.z = value;
    }

    public Vec3(@NotNull Vec4 vec4)
    {
        this.x = vec4.x;
        this.y = vec4.y;
        this.z = vec4.z;
    }
    public Vec2 getXY()
    {
        return new Vec2(x, y);
    }
    public @NotNull String toString()
    {
        return "(" + x + "|" + y + "|" + z +")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vec3 other = (Vec3) obj;

        return this.x == other.x &&
            this.y == other.y &&
            this.z == other.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }

    public float getDistance(@NotNull Vec3 pos)
    {
        int deltaX = pos.x - x;
        int deltaY = pos.y - y;
        int deltaZ = pos.z - z;

        return (float) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    public @NotNull Vec3 add(@NotNull Vec3 vec)
    {
        int resX = this.x + vec.x;
        int resY = this.y + vec.y;
        int resZ = this.z + vec.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 sub(@NotNull Vec3 vec)
    {
       int resX =  this.x - vec.x;
       int resY =  this.y - vec.y;
       int resZ =  this.z - vec.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 subr(@NotNull Vec3 vec)
    {
        int resX = vec.x - this.x;
        int resY = vec.y - this.y;
        int resZ = vec.z - this.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 mult(@NotNull Vec3 vec)
    {
        int resX = this.x * vec.x;
        int resY = this.y * vec.y;
        int resZ = this.z * vec.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 div(@NotNull Vec3 vec)
    {
        int resX = this.x / vec.x;
        int resY = this.y / vec.y;
        int resZ = this.z / vec.z;
        return new Vec3(resX, resY, resZ);
    }

    public @NotNull Vec3 divr(@NotNull Vec3 vec)
    {
        int resX = vec.x / this.x;
        int resY = vec.y / this.y;
        int resZ = vec.z / this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 add(int value)
    {
        int resX = this.x + value;
        int resY = this.y + value;
        int resZ = this.z + value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 sub(int value)
    {
        int resX = this.x - value;
        int resY = this.y - value;
        int resZ = this.z - value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 subr(int value)
    {
        int resX = value - this.x;
        int resY = value - this.y;
        int resZ = value - this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 mult(int value)
    {
        int resX = this.x * value;
        int resY = this.y * value;
        int resZ = this.z * value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 div(int value)
    {
        int resX = this.x / value;
        int resY = this.y / value;
        int resZ = this.z / value;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 divr(int value)
    {
        int resX = value / this.x;
        int resY = value / this.y;
        int resZ = value / this.z;
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 add(float value)
    {
        int resX = (int)(this.x + value);
        int resY = (int)(this.y + value);
        int resZ = (int)(this.z + value);
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 sub(float value)
    {
        int resX = (int)(this.x - value);
        int resY = (int)(this.y - value);
        int resZ = (int)(this.z - value);
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 subr(float value)
    {
        int resX =  (int)(value - this.x);
        int resY =  (int)(value - this.y);
        int resZ = (int)(value - this.z);
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 mult(float value)
    {
        int resX = (int)(this.x * value);
        int resY = (int)(this.y * value);
        int resZ = (int)(this.z * value);
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 div(float value)
    {
        int resX = (int)(this.x / value);
        int resY = (int)(this.y / value);
        int resZ = (int)(this.z / value);
        return new Vec3(resX, resY, resZ);
    }

    @Override
    public @NotNull Vec3 divr(float value)
    {
        int resX = (int)(value / this.x);
        int resY = (int)(value / this.y);
        int resZ = (int)(value / this.z);
        return new Vec3(resX, resY, resZ);
    }
}
