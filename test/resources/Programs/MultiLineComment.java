/* Here is a multi line comment that should not be included. */
public class Comment {
    /**/
    public static void main(String[/* /* /* /* Here's another one. */] args) { /*




    */
        int x = 1; /* /* /*
        Here's another one.
        */
        /* Here are some tokens that shouldn't be included:
        length
        main
        public
        static
         */
    }
}
/* Here's another one.
 */
