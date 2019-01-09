##Array Views

A "view" here refers to a read-only wrapper class that provides access to certain elements in an array
given an index.

## Types of Views

`0+` partitions of an array (`PartionedArrayView`)

`1` partition of an array (`ContiguousArrayView`)

`0+` elements of an array (`SeparatedArrayView`)

Elements that satisfy a predicate (`FilteredArrayView`)