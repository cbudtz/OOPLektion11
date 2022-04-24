package data;

import java.io.Serializable;

public record Course(String name, int number) implements Serializable {}
