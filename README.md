# RandomLoot

## About
A small Wurm Unlimited mod that adds a new command that helps players
divvy up the loot they got.

## Usage

`#roll`
The resulting message (usually):
````
[XX:XX:XX] You rolled for the loot. Rolling for X people in Y meters.
1. RANDOM_PLAYER_1
2. RANDOM_PLAYER_2
...
X. RANDOM_PLAYER_X
````
And to other players:
````
[XX:XX:XX] ROLLING_PLAYER rolled for the loot. Rolling for X people in Y meters.
1. RANDOM_PLAYER_1
2. RANDOM_PLAYER_2
...
X. RANDOM_PLAYER_X
````

**Or:**

`#roll dragon corpse`
The resulting message (usually):
````
[XX:XX:XX] You rolled for dragon corpse. Rolling for X people in Y meters.
1. RANDOM_PLAYER_1
2. RANDOM_PLAYER_2
...
X. RANDOM_PLAYER_X
````
And to other players:
````
[XX:XX:XX] ROLLING_PLAYER rolled for dragon corpse. Rolling for X people in Y meters.
1. RANDOM_PLAYER_1
2. RANDOM_PLAYER_2
...
X. RANDOM_PLAYER_X
````


## Configurable
 - distMeters: How many meters radius from the rolling player it should count players
 - includeGmsInRolls: Whether GMs should count to the roll.
 - command: What the command should be.