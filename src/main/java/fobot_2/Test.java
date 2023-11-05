package fobot_2;

import fobot_2.entity.Robot;
import fobot_2.visuals.EventConfig;
import fobot_2.visuals.RenderConfig;
import fobot_2.visuals.Renderer;
import javafx.application.Application;

public class Test
{
    public static void main(String[] args)
    {
        World.createWorld(2,2);
        Renderer.configure(() ->
        {
            new Robot(0,0);
            System.out.println("Hello World!");


            for (int i = 0; i < 100000; i++)
            {
                if(i == 10000)
                {
                    System.out.println("Exiting");
                    System.exit(0);
                }
                System.out.println("Run");
            }


        });
        Application.launch(Renderer.class);
    }
}
