# Gamomat-Challenge

## Information
A Slot Machine REST Endpoint using Spring Framework.

Running it creates an /spin endpoint on the local machine with port 8080

In the current iteration it's configurable through the slotMachine.properties config file,
allowing the addition of further reels, change reel order, extend the winning subset and winning lines.

The Subset is built by creating a single array from our reels the index are mapped the following for a 3x3 subset.

| R1  | R2  | R3  |
|-----|-----|-----|
| 0   | 1   | 2   |
| 3   | 4   | 5   |
| 6   | 7   | 8   |

We simply concatenate the values at the indexes and compare 
if the combination is a winning one, then write the line indexes + the winning amounts
to a map for accumulation.

## Config
###H ow to use slotMachine.properties
I mentioned before the config file, now I am going to further explain and mention some caveats
#### Its required currently to abstain from whitespaces in the config file
#### *reels=reel1,reel2,reel3*
The reel property is a simple csv which later gets filled with the reel elements they are
referring to, and shows the order in which the reels are spinning
#### *reel1=AAA....*
Something I call reelString, they are split into single letters to build the reel elements itself,
they get linked by name here, you could name your reel whatever you want as it matches the name in the reels property
#### *reelRows= 3*
Amount of rows being considered for the winning subset, only values >= 1 allowed
####*winLines = 0,1,2;3,4,5*
A winning line is described by a comma separated list of indexes the "," separates the values and ";" the value pairs.
#### *winCondition = AAA,0.1;BBB,0.15;CCC,0.2*
We know when we win, if the concatenated values by the win line are equal to the win condition

## Things to Explore/Improve
Currently, the machine only does the required + optional stuff, but here are some things to add:

- Wildcard ReelElements A*A
- calculation on the winning probabilities aka rtp
- add parameters to the spin endpoint, afaik you can raise the "bet"
- create a session based multithreaded servlet, now the machine knows how much money you threw in and adds it
- create a more verbose config validator
