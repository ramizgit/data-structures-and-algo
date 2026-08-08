package lld.amazonlocker.enums;

/*
Why separate PackageSize and LockerSize?

Because they represent different things.

A:
SMALL package

can fit into:

SMALL locker
MEDIUM locker
LARGE locker

But a:

LARGE package

can only fit into:

LARGE locker

So later our allocation strategy can implement:

Package SMALL
    ↓
choose smallest suitable available locker
 */

public enum LockerSize {

    SMALL,
    MEDIUM,
    LARGE
}
