package linkedhashmap;

import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * You are given a list of food’s positions in row-column order. When a snake eats the food, its length and the game’s score both increase by 1.
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 *
 * Example:
 * Given width = 3, height = 2, and food = [[1,2],[0,1]].
 * Snake snake = new Snake(width, height, food);
 *
 * Initially the snake appears at position (0,0) and the food at (1,2).
 * |S| | |
 * | | |F|
 *
 * snake.move("R"); -> Returns 0
 * | |S| |
 * | | |F|
 *
 * snake.move("D"); -> Returns 0
 * | | | |
 * | |S|F|
 *
 * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
 * | |F| |
 * | |S|S|
 *
 * snake.move("U"); -> Returns 1
 * | |F|S|
 * | | |S|
 *
 * snake.move("L"); -> Returns 2 (Snake eats the second food)
 * | |S|S|
 * | | |S|
 *
 * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
 */
class SnakeGame{

    class Position {
        int row;
        int col;
        Position(int x, int y){
            this.row =x;
            this.col =y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position pair = (Position) o;
            return row == pair.row && col == pair.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    int width;
    int height;
    int x;
    int y;
    LinkedHashSet<Position> snake;
    LinkedHashSet<Position> foods;

    SnakeGame(int width,int height,int[][] foods){
        this.width=width;
        this.height=height;
        this.snake=new LinkedHashSet<>();
        this.foods=new LinkedHashSet<>();
        for(int i=0;i<foods.length;i++){
            this.foods.add(new Position(foods[i][0],foods[i][1]));
        }
        snake.add(new Position(0,0));
        this.x=0;
        this.y=0;
    }

    boolean validate(int x,int y){
        if(snake.contains(new Position(x,y)))return true;

        if(x<0 || x>=width)return true;
        if(y<0 || y>=height)return true;

        return false;
    }

    void changeSnakeSize(int x,int y){
        Position food=foods.stream().findFirst().get();
        // If food present in the coordinate where we are moving increase size of the snake
        // else move from end of snake to beginning
        if(food.equals(new Position(x,y))){
            snake.add(new Position(x,y));
            foods.remove(food);
        }else{
            snake.add(new Position(x,y));
            //Remove the last element from the linkedHashSet
            Position p=snake.stream().findFirst().get();
            snake.remove(p);
        }
    }

    int move(Character c){
        if(c.equals('U')){
            if(validate(x-1,y))return -1;
            x--;
            changeSnakeSize(x,y);
        }else if(c.equals('D')){
            if(validate(x+1,y))return -1;
            x++;
            changeSnakeSize(x,y);
        }else if(c.equals('L')){
            if(validate(x,y-1))return -1;
            y--;
            changeSnakeSize(x,y);
        }else if(c.equals('R')){
            if(validate(x,y+1))return -1;
            y++;
            changeSnakeSize(x,y);
        }else{
            System.out.println("Invalid input");
            return -1;
        }
        return snake.size()-1;
    }

    public static void main(String[] args) {

        SnakeGame snake=new SnakeGame(2, 3, new int[][]{{1,2},{0,1}});

        System.out.println(snake.move('R')); //-> Returns 0
        System.out.println(snake.move('D')); //-> Returns 0
        System.out.println(snake.move('R')); //-> Returns 1
        System.out.println(snake.move('U')); //-> Returns 1
        System.out.println(snake.move('L')); //-> Returns 2
        System.out.println(snake.move('U')); //-> Returns -1
    }
}