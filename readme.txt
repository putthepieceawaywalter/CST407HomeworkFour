
Notes about my project

I struggled a lot more with viewing my favorited items than I did with any other part of this app.

I decided the easiest way to get a smooth submission would be to create a second recycler view (one for favorited items and one for viewing random drinks) 

If you press the "Scroll through some drinks" button the app will ask thecocktailapi for 10 random drinks and display them to the user.  The user can press the heart button which will add them to the local likedDrinks list and to the firebase database (if they press the heart button again it de-selects and removes the item from the database).  When the user presses "view my favorited drinks" the user can scroll through a recycler view of drink pictures with their titles that the app has gotten from the firebase database.  As of right now, while viewing favorited drinks, they cannot click the image to view the description (ingredients and instructions) from that screen but I plan to have that implemented for my project submission.

Another major flaw is that a user cannot currently un favorite something from the view my favorited drinks screen.  Again I plan to implement that in the project submission.

I've never made an android app with data that persists before so this was super fun, its also extremeley relevant for my senior project.
