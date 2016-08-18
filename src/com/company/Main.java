package com.company;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;
import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();

        //creates player
        Player player = new Player(10,10);

        //create enemy
        Enemy enemy = new Enemy(1,1);


        //Startmeddelande
        terminal.putCharacter('P');
        terminal.putCharacter('u');
        terminal.putCharacter('s');
        terminal.putCharacter('h');
        terminal.putCharacter(' ');
        terminal.putCharacter('A');
        terminal.putCharacter('n');
        terminal.putCharacter('y');
        terminal.putCharacter(' ');
        terminal.putCharacter('B');
        terminal.putCharacter('u');
        terminal.putCharacter('t');
        terminal.putCharacter('t');
        terminal.putCharacter('o');
        terminal.putCharacter('n');
        terminal.putCharacter(' ');
        terminal.putCharacter('T');
        terminal.putCharacter('o');
        terminal.putCharacter(' ');
        terminal.putCharacter('S');
        terminal.putCharacter('t');
        terminal.putCharacter('a');
        terminal.putCharacter('r');
        terminal.putCharacter('t');
        terminal.putCharacter('.');
        terminal.putCharacter('.');
        terminal.putCharacter('.');


        boolean run = true;

        while(run){ //Wait for a key to be pressed

            MovePlayer(player, terminal);

            if(!GameLogic(player,enemy)){
                run = false;
            }

            UpdateScreen(player, enemy, terminal);

            GameLogic(player, enemy);


        }//End while - Main Game loop()


        terminal.moveCursor(0,0);
        terminal.putCharacter('G');
        terminal.moveCursor(1,0);
        terminal.putCharacter('A');
        terminal.moveCursor(2,0);
        terminal.putCharacter('M');
        terminal.moveCursor(3,0);
        terminal.putCharacter('E');
        terminal.moveCursor(4,0);
        terminal.putCharacter(' ');
        terminal.moveCursor(5,0);
        terminal.putCharacter('O');
        terminal.moveCursor(6,0);
        terminal.putCharacter('V');
        terminal.moveCursor(7,0);
        terminal.putCharacter('E');
        terminal.moveCursor(8,0);
        terminal.putCharacter('R');


    }//End Main

    private static boolean GameLogic(Player player, Enemy enemy){


        if(player.x < enemy.x){
            enemy.x -= 1;
        }
        if(player.x > enemy.x){
            enemy.x += 1;
        }

        if(player.y < enemy.y){
            enemy.y -= 1;
        }
        if(player.y > enemy.y){
            enemy.y += 1;
        }

        if(player.x == enemy.x && player.y == enemy.y){
            return false;
        }

        return true;

    }//End GameLogic

    private static void MovePlayer(Player player, Terminal terminal) throws InterruptedException {

        Key key;
        do{
            Thread.sleep(5);
            key =terminal.readInput();

        } while(key == null);

        switch (key.getKind()){

            case ArrowLeft:
                if (player.x > 0) {
                    player.x -= 2;
                }
                break;
            case ArrowUp:
                if(player.y > 0) {
                    player.y -= 2;
                }
                break;
            case ArrowRight:
                if(player.x < 40) {
                    player.x += 2;
                }
                break;
            case ArrowDown:
                if(player.y < 40) {
                    player.y += 2;
                }
                break;
            default:
                player.x += 0;
                player.y += 0;
                //strunta i deafaultvärdet!
                //ev. defaultvärde
        }//End switch

        System.out.println(key.getCharacter()+ " " + key.getKind());

    }//End MovePlayer

    public static void UpdateScreen(Player player, Enemy enemy, Terminal terminal){

        terminal.clearScreen();
        terminal.moveCursor(player.x,player.y);
        terminal.putCharacter('O');
        terminal.moveCursor(0,0);

        terminal.moveCursor(enemy.x,enemy.y);
        terminal.putCharacter('X');
        terminal.moveCursor(0,0);


    }



}//End Class Main
