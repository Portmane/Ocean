package io.domain;

public final class Views {
    public interface Id {}
    public interface IdAndText extends Id {}

    public interface FullMessage extends IdAndText {}
}
