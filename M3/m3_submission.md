<table><tr><td> <em>Assignment: </em> IT114 - Number Guesser</td></tr>
<tr><td> <em>Student: </em> Isaac Guerrero (iag8)</td></tr>
<tr><td> <em>Generated: </em> 2/17/2023 7:45:51 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-006-S23/it114-number-guesser/grade/iag8" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <ol><li>Create the below branch name</li><li>Implement the NumberGuess4 example from the lesson/slides</li><li>Add/commit the files as-is from the lesson material (this is the base template)</li><li>Pick two (2) of the following options to implement</li><ol><li>Display higher or lower as a hint after a wrong guess</li><li>Implement anti-data tampering of the save file data (reject user direct edits)</li><li>Add a difficulty selector that adjusts the max strikes per level</li><li>Display a cold, warm, hot indicator based on how close to the correct value the guess is (example, 10 numbers away is cold, 5 numbers away is warm, 2 numbers away is hot; adjust these per your preference)</li><li>Add a hint command that can be used once per level and only after 2 strikes have been used that reduces the range around the correct number (i.e., number is 5 and range is initially 1-15, new range could be 3-8 as a hint)</li><li>Implement separate save files based on a "What's your name?" prompt at the start of the game</li></ol><li>Fill in the below deliverables</li><li>Create an m3_submission.md file and fill in the markdown from this tool when you're done</li><li>Git add/commit/push your changes to the HW branch</li><li>Create a pull request to main</li><li>Complete the pull request</li><li>Grab the link to the m3_submission.md from the main branch and submit that direct link to github</li></ol></td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Implementation 1 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://user-images.githubusercontent.com/98345291/219444212-22a73630-3f69-4ac3-bf8c-f91b14b6fb24.png"/></td></tr>
<tr><td> <em>Caption:</em> <p>Photo of my code with comments provoing of my name and date<br></p>
</td></tr>
<tr><td><img width="768px" src="https://user-images.githubusercontent.com/98345291/219445025-83e7bb78-750b-41d6-87c8-75b6adee7366.png"/></td></tr>
<tr><td> <em>Caption:</em> <p>Proof of code running and showing my implemented features. Displays the hot and<br>cold feature which displays cold when the number is greater then 6 numbers<br>away from the guess. It displays warm when the guess is within 4-5<br>guesseds away and it displays how when the guess is within 3 guesses<br>away.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>The logic behind my implementation was to start of by using a if<br>and else if statement. The reason for this is because I knew I<br>wanted to display 3 different results, those being cold, warm, and hot. Depending<br>on the value it would display one of them. Since being hot means<br>you&#39;re really close, it has to be the last option. This means working<br>backwards so starting with cold to hot. This means that my first if<br>statement must be the one that checks for the furthest option, my else<br>if is next which checks for a value thats close but not to<br>close and then the else is the one where it displays hot because<br>its the only other option.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Implementation 2 (one of the picked items) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Two Screenshots: Add a screenshot demonstrating the feature during runtime; Add a screenshot (or so) of the snippets of code that implement the feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://user-images.githubusercontent.com/98345291/219444212-22a73630-3f69-4ac3-bf8c-f91b14b6fb24.png"/></td></tr>
<tr><td> <em>Caption:</em> <p>Photo of my code with comments provoing of my name and date<br></p>
</td></tr>
<tr><td><img width="768px" src="https://user-images.githubusercontent.com/98345291/219445025-83e7bb78-750b-41d6-87c8-75b6adee7366.png"/></td></tr>
<tr><td> <em>Caption:</em> <p>Proof of code running and showing my implemented features. Displays the, your next<br>guess should be higher or your next guess should be lower text depending<br>on if your guess is higher or lower then the guess.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the logic behind your implementation</td></tr>
<tr><td> <em>Response:</em> <p>For this all we have to do is compare the guess to the<br>number we are trying to find. If guess is less then the value<br>then we print the guess should be higher and if the guess is<br>greater then the value then we print the guess should be smaller.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a link to the related pull request of this hw</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/Isaag1/IT114-006/pull/9">https://github.com/Isaag1/IT114-006/pull/9</a> </td></tr>
<tr><td> <em>Sub-Task 2: </em> Discuss anything you learned during this lesson/hw or any struggles you had</td></tr>
<tr><td> <em>Response:</em> <p>I had trouble figuring out how I wanted to implement the cold and<br>hot feature and was going about it the wrong way till I read<br>a bit more of the slides and remmebered one of the examples we<br>learned in class.<br></p><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-006-S23/it114-number-guesser/grade/iag8" target="_blank">Grading</a></td></tr></table>