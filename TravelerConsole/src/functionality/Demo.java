package functionality;
// package functionality;
//
// import java.util.ArrayList;
//
// import exceptions.InvalidAuthorException;
// import exceptions.InvalidDataException;
// import models.Comment;
// import models.Destination;
// import models.Location;
// import models.User;
//
// public class Demo {
//
// public static void main(String[] args) {
// char[] passwordForJohn = { 'a', 'b', 'c', '1', '2', '3' }; // John's
// // password
// char[] passwordForMichael = { 'i', 'A', 'm', 'M', 'J', '!' }; // Michael's
// // password
//
// try {
// User john = UserFactory.createUser("John", "Lennon", "abc123",
// "j_lennon@gmail.com",
// "I have a natural talent for exploring new and interesting places! I like to
// travel a lot and am eager to learn more about Bulgaria!");
// User michael = UserFactory.createUser("Michael", "Jackson", "IamMJ!",
// "m_jackson@yahoo.com",
// "I am interested in music, dancing and travelling! I enjoy spending time
// outdoors!");
//
// Location lovechLocation = new Location(43.08, 24.43);
// Location plevenLocation = new Location(43.25, 24.37);
//
// Destination lovech = new Destination("Lovech City",
// "A lovely bulgarian city situated in the north-central part of the country.
// It is located about 150 kilometres northeast from the capital city of
// Sofia.",
// lovechLocation);
// Destination pleven = new Destination("Pleven City",
// "Pleven is the seventh most populous city in Bulgaria. Located in the
// northern part of the country.",
// plevenLocation);
//
// john.makeAComment(pleven, "Wow!! What a great place! I love it!"); // John
// // adds
// // a
// // comment
// // about
// // Pleven
// Comment johnLastCommentAboutPleven = pleven.getComments().get(0);
// michael.likeAComment(johnLastCommentAboutPleven); // Michael likes
// // John's
// // comment about
// // Pleven
//
// john.addVisitedDestination(lovech);
// michael.addVisitedDestination(pleven);
// michael.addVisitedDestination(lovech);
//
// try {
// printUserInfo(john); // printing information about the user
// printUserInfo(michael); // printing information about the user
// } catch (CloneNotSupportedException e) {
// System.out.println("Something went wrong!"); // (some message
// // here)
// }
//
// } catch (InvalidDataException e) {
// e.getMessage();
// e.printStackTrace();
// } catch (InvalidAuthorException e1) {
// e1.getMessage();
// e1.printStackTrace();
// }
//
// }
//
// public static void printUserInfo(User user) throws CloneNotSupportedException
// { // print
// // all
// // the
// // information
// // of
// // the
// // user
// System.out.println("======== Information about " + user.getFirstName() + "
// ========"); // heading
// System.out.println("Full name: " + user.getFirstName() + " " +
// user.getLastName()); // full
// // name
// System.out.println("E-mail: " + user.getEmail()); // e-mail address
// System.out.println("Personal information: " + user.getDescription()); //
// personal
// // information
// System.err.print("User password: "); // password (check)
// char[] tempUserPassword = user.getPassword();
// for (int i = 0; i < tempUserPassword.length; i++) {
// System.err.print(tempUserPassword[i]);
// }
// System.err.println(); // new line
// System.out.println("\n--- Visited Places: ---"); // sub heading
// ArrayList<Destination> visitedPlaces = user.getVisitedPlaces(); // all
// // visited
// // destinations
// for (Destination destination : visitedPlaces) {
// printDestinationInfo(destination); // information about the
// // destination
// }
// System.out.println("----------------");
// System.out.println("======================================\n");
// }
//
// public static void printCommentInfo(Comment comment) throws
// CloneNotSupportedException { // print
// // information
// // about
// // the
// // comment
// System.out.println("> Comment from " + comment.getAuthor().getFirstName() + "
// "
// + comment.getAuthor().getLastName() + ": \"" + comment.getText() + "\""); //
// comment
// // information
// System.out.println("* Number of likes: " + comment.getNumberOfLikes()); //
// number
// // of
// // likes
// ArrayList<User> userLikersOfComment = comment.getUserLikers();
// System.out.print("* Users who liked the comment: ");
// for (User userLiker : userLikersOfComment) {
// System.out.print(userLiker.getFirstName() + " " + userLiker.getLastName() + "
// "); // prints
// // the
// // name
// // of
// // each
// // user
// // who
// // liked
// // the
// // comment
// }
// System.out.println(); // new line
// }
//
// public static void printDestinationInfo(Destination destination) throws
// CloneNotSupportedException { // print
// // information
// // about
// // the
// // destination
// System.out.println("\n**** " + destination.getName() + " ****"); // name
// // of
// // destination
// System.out.println("Description: " + destination.getDescription()); //
// information
// System.out.println("- Location -"); // location sub heading
// Location location = destination.getLocation();
// System.out.println("Latitude: " + location.getLatitude() + "N"); // latitude
// System.out.println("Longitude: " + location.getLongitude() + "E"); //
// longitude
// ArrayList<Comment> comments = destination.getComments();
// if (comments.size() > 0) { // if there are any comments
// System.out.println("- Comments -"); // comments sub heading
// for (Comment comment : comments) {
// printCommentInfo(comment); // comment information printing
// }
// System.out.println("-------------------");
// }
// System.out.println("****************");
// }
//
// }
