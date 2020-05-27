# DynamicProgrammingApproach-GreedyApproach

Explanation of the Expected Quality Player Selection
Suppose that you are the manager of a football club. You are expected to sign free contract
players for your team with the given budget of $X. The owner of the club will fire you if you
spend money more than $X (You are allowed to spend less than $X). You are considering N
different positions (such as Goalkeeper, Defender, Forward etc.). For each position in a team,
there are K players who are available to play that specific position. However, you must sign at
most one player for each position. If you do not sign any players at a particular position, then
you plan to stick with the players you already have for that position.
Each player has three main information:
 The player’s position,
 The amount of money that will cost to sign the player,
 The player’s rating.
The goal is to maximize the total ratings of players you signed while spending no more than
$X.
Your algorithm should output the total ratings and total price you spent on signings with the
list of the players you signed.
Operations:
To maximize total ratings without spending more than $X, you are expected to both design DP
and Greedy approaches.
Methods you must code:
 Dynamic Programming Approach
 Greedy Approach
Note: You will get the ratings, signing costs and positions of each player from the “input.xlsx”
file. In the matrix, columns represent name, position, rating, price, respectively; whereas rows
show players. You are allowed to convert .xlsx file into a corresponding .txt files (or whatever
you want) before calling file read method in Java.
