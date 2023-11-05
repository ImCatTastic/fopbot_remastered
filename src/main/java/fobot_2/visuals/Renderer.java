package fobot_2.visuals;

import com.sun.prism.GraphicsPipeline;
import fobot_2.Defaults;
import fobot_2.World;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

public class Renderer extends Application
{
    public enum KeyPressType
    {
        TYPED,
        PRESSED,
        RELEASED
    }
    private final Set<Integer> keysPressed = new HashSet<>();


    public Renderer()
    {
        if(instance == null)
            instance = this;
    }
    private static Renderer instance;
    public static Renderer getInstance()
    {
        if(instance == null)
            throw new RuntimeException("Instance not created yet!");

        return instance;
    }


    private static Runnable mainCode;
    private static RenderConfig renderConfig;
    private static EventConfig eventConfig;
    private static World world;
    public static void configure(@NotNull Runnable mainCode, @NotNull RenderConfig renderConfig, @NotNull EventConfig eventConfig)
    {
        if(isInit)
            return;

        Renderer.renderConfig = renderConfig;
        Renderer.eventConfig = eventConfig;
        Renderer.mainCode = mainCode;
        isInit = true;
    }

    public static void configure(@NotNull Runnable mainCode, @NotNull RenderConfig renderConfig)
    {
        configure(mainCode, renderConfig, new EventConfig());
    }

    public static void configure(@NotNull Runnable mainCode, @NotNull EventConfig eventConfig)
    {
        configure(mainCode, new RenderConfig(Defaults.DEFAULT_COLOR_PROFILE), eventConfig);
    }

    public static void configure(@NotNull Runnable mainCode)
    {
        configure(mainCode, new RenderConfig(Defaults.DEFAULT_COLOR_PROFILE), new EventConfig());
    }
    private static int start_guiWidth = 0;
    private static int start_guiHeight = 0;
    private static int guiWidth = 0;
    private static int guiHeight = 0;
    private static int boardWidth;
    private static int boardHeight;











    private final ExecutorService eventExecutorService = Executors.newSingleThreadExecutor();
    private static final ArrayList<BiConsumer<KeyEvent, KeyPressType>> keyListeners = new ArrayList<>();
    public static void addKeyListener(BiConsumer<KeyEvent, KeyPressType> action)
    {
        keyListeners.add(action);
    }


    private static Pane root;
    static StackPane sp;
    private static Scene scene;
    private static Canvas canvas;
    private static GraphicsContext gc;
    private static Pane holder;




    private static double scale = 1;

    private static boolean isInit = false;
    @Override
    public void start(Stage primaryStage)
    {
        if(!isInit)
            throw new RuntimeException("Renderer not initialized!");

        world = World.getInstance();

        boardWidth = renderConfig.borderThickness * (World.getInstance().getWidth() + 1) + renderConfig.fieldSize * world.getWidth();
        boardHeight = renderConfig.borderThickness * (world.getHeight() + 1) + renderConfig.fieldSize * world.getHeight();
        start_guiWidth = boardWidth + (2 * renderConfig.marginSize);
        start_guiHeight = boardHeight + (2 * renderConfig.marginSize);
        guiWidth = start_guiWidth;
        guiHeight = start_guiHeight;

        root = new StackPane();
        root.setStyle(String.format("-fx-background-color: rgba(%s, %s, %s, %s);",
            renderConfig.profile.backgroundColor().getRed() * 255,
            renderConfig.profile.backgroundColor().getGreen() * 255,
            renderConfig.profile.backgroundColor().getBlue() * 255,
            renderConfig.profile.backgroundColor().getOpacity() * 255
            ));

        scene = new Scene(root, guiWidth, guiHeight, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FOPBot - Remastered++");

        if(eventConfig.enableKeyEvents)
        {
            scene.setOnKeyTyped(e ->
            {
                for (BiConsumer<KeyEvent, KeyPressType> keyListener : keyListeners)
                    eventExecutorService.submit(() -> keyListener.accept(e, KeyPressType.TYPED));
            });

            scene.setOnKeyPressed(e ->
            {
                keysPressed.add(e.getCode().getCode());

                for (BiConsumer<KeyEvent, KeyPressType> keyListener : keyListeners)
                    eventExecutorService.submit(() -> keyListener.accept(e, KeyPressType.PRESSED));
            });

            scene.setOnKeyReleased(e ->
            {
                keysPressed.remove(e.getCode().getCode());

                for (BiConsumer<KeyEvent, KeyPressType> keyListener : keyListeners)
                    eventExecutorService.submit(() -> keyListener.accept(e, KeyPressType.RELEASED));
            });
        }

        if(eventConfig.enableResizeEvents)
        {
            scene.widthProperty().addListener((observable, oldValue, newValue) ->
            {
                guiWidth = newValue.intValue();
                resize();
            });
            scene.heightProperty().addListener((observable, oldValue, newValue) ->
            {
                guiHeight = newValue.intValue();
                resize();
            });
        }

        sp = new StackPane();
        sp.setPrefSize(boardWidth * scale, boardHeight * scale);
        root.getChildren().add(sp);

        canvas = new Canvas(boardWidth * scale, boardHeight * scale);
        gc = canvas.getGraphicsContext2D();
        sp.getChildren().add(canvas);

        holder = new Pane();
        holder.setMaxSize(boardWidth - (borderOffset*2), boardHeight - (borderOffset*2));
        sp.getChildren().add(holder);

        primaryStage.show();

        if(mainCode != null)
        {
            Thread mainThread = new Thread(mainCode);
            mainThread.start();
        }

        getCurrentGraphicsPipeline();
    }





    private double fieldOffset;
    private double borderOffset;
    private double largeOffset;
    private double yStart;
    private double doubleBorderOffset;
    static Text textNode = null;
    static Font font = null;
    private void resize()
    {
        scale = Math.min(
            (float) guiWidth / (float) start_guiWidth,
            (float) guiHeight / (float) start_guiHeight);

        double scaledBoardW = boardWidth * scale;
        double scaledBoardH = boardHeight * scale;
        calcOffsets();

        sp.setPrefSize(scaledBoardW, scaledBoardH);
        holder.setMaxSize(scaledBoardW - doubleBorderOffset, scaledBoardH - doubleBorderOffset);

        canvas.setWidth(scaledBoardW);
        canvas.setHeight(scaledBoardH);
        //world.rescaleRobotImages(100);
        paint();
        //guiRobots.forEach((s, n) -> n.resize());
    }

    private void calcOffsets()
    {
        fieldOffset = renderConfig.fieldSize * scale;
        borderOffset = renderConfig.borderThickness * scale;
        largeOffset = fieldOffset + borderOffset;
        doubleBorderOffset = borderOffset * 2;
        yStart = (world.getHeight() - 1) * largeOffset + borderOffset;

        font = Font.font("Arial", FontWeight.BLACK,24 * scale);
        textNode = new Text("99");
        textNode.setFont(font);
        textNode.setBoundsType(TextBoundsType.VISUAL);
    }

    private void paint()
    {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        paintGrid();
    }
    private void paintGrid()
    {
        double canvasW = gc.getCanvas().getWidth();
        double canvasH = gc.getCanvas().getHeight();

        gc.setFill(renderConfig.profile.borderColor());
        gc.fillRect(0, 0, canvasW, canvasH);

        //gc.setFill(renderConfig.profile.borderColor());
        //gc.fillRect(borderOffset, borderOffset, canvasW - doubleBorderOffset, canvasH - doubleBorderOffset);

        gc.setFill(renderConfig.profile.fieldColor());

        double xOffset = borderOffset;
        double yOffset = yStart;

        double lineLength = largeOffset + borderOffset;

        for (int y = 0; y < world.getHeight(); y++)
        {
            for (int x = 0; x < world.getWidth(); x++)
            {
                gc.fillRect(xOffset, yOffset, fieldOffset, fieldOffset);

                //Vec2 pos = new Vec2(x, y);
                //var wallData = walls.get(pos);

                /*
                //Draw corresponding wall
                if(wallData != null)
                {
                    gc.setFill(wallColor);

                    if(wallData[0])
                        gc.fillRect(xOffset - borderOffset, yOffset - borderOffset, lineLength, borderOffset);

                    if(wallData[1])
                        gc.fillRect(xOffset + fieldOffset, yOffset - borderOffset, borderOffset, lineLength);

                    gc.setFill(fieldColor);
                }

                var coinCount = coins.get(pos);

                if (coinCount != null)
                {
                    gc.setFill(new Color(1,0,0,1));
                    gc.fillOval(xOffset + (fieldInnerOffset/2), yOffset + (fieldInnerOffset/2), fieldOffset - fieldInnerOffset, fieldOffset - fieldInnerOffset);

                    gc.setFont(font);
                    gc.setFill(Color.PURPLE);

                    gc.setFill(Color.YELLOW);
                    //gc.fillRect(xOffset, yOffset, textWidth2 , textHeight1);

                    gc.setFill(Color.WHITE);
                    // Draw text using fillText
                    double textHeight = textNode.getLayoutBounds().getHeight();
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.fillText(coinCount.toString(), xOffset + (fieldOffset/2) , yOffset + (textHeight/2) + (fieldOffset/2));

                    gc.setFill(fieldColor);
                }

                if(blocks.contains(pos))
                {
                    gc.setFill(new Color(1,1,1,1));
                    gc.fillRect(xOffset - borderOffset, yOffset - borderOffset, fieldOffset + doubleBorderOffset, fieldOffset + doubleBorderOffset);
                    gc.setFill(fieldColor);
                    gc.fillRect(xOffset, yOffset, fieldOffset, fieldOffset);
                    gc.setFill(Color.WHITE);
                    gc.fillRect(xOffset + doubleBorderOffset, yOffset + doubleBorderOffset, fieldOffset - (doubleBorderOffset*2), fieldOffset - (doubleBorderOffset*2));
                    gc.setFill(fieldColor);
                }
                */

                xOffset += largeOffset;
            }

            xOffset = borderOffset;
            yOffset -= largeOffset;
        }
    }














    private static void getCurrentGraphicsPipeline()
    {
        String msg = GraphicsPipeline.getPipeline().getClass().getName();

        if(msg.equals("com.sun.prism.d3d.D3DPipeline"))
            System.out.println("Note: Using Direct3D pipleline.");

        else
            System.out.println("Note: Using " + msg + " pipleline.");
    }
}
