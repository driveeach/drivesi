import java.applet.*;
import java.awt.*;

public class ControlPanel extends Frame 
{
  Kernel kernel ;
  Button runButton = new Button("run");
  Button stepButton = new Button("step");
  Button resetButton = new Button("reset");
  Button exitButton = new Button("exit");
  Button b0 = new Button("page " + (0));
  Button b1 = new Button("page " + (1));
  Button b2 = new Button("page " + (2));
  Button b3 = new Button("page " + (3));
  Button b4 = new Button("page " + (4));
  Button b5 = new Button("page " + (5));
  Button b6 = new Button("page " + (6));
  Button b7 = new Button("page " + (7));
  Button b8 = new Button("page " + (8));
  Button b9 = new Button("page " + (9));
  Button b10 = new Button("page " + (10));
  Button b11 = new Button("page " + (11));
  Button b12 = new Button("page " + (12));
  Button b13 = new Button("page " + (13));
  Button b14 = new Button("page " + (14));
  Button b15 = new Button("page " + (15));
  Button b16 = new Button("page " + (16));
  Button b17 = new Button("page " + (17));
  Button b18 = new Button("page " + (18));
  Button b19 = new Button("page " + (19));
  Button b20 = new Button("page " + (20));
  Button b21 = new Button("page " + (21));
  Button b22 = new Button("page " + (22));
  Button b23 = new Button("page " + (23));
  Button b24 = new Button("page " + (24));
  Button b25 = new Button("page " + (25));
  Button b26 = new Button("page " + (26));
  Button b27 = new Button("page " + (27));
  Button b28 = new Button("page " + (28));
  Button b29 = new Button("page " + (29));
  Button b30 = new Button("page " + (30));
  Button b31 = new Button("page " + (31));
  Button b32 = new Button("page " + (32));
  Button b33 = new Button("page " + (33));
  Button b34 = new Button("page " + (34));
  Button b35 = new Button("page " + (35));
  Button b36 = new Button("page " + (36));
  Button b37 = new Button("page " + (37));
  Button b38 = new Button("page " + (38));
  Button b39 = new Button("page " + (39));
  Button b40 = new Button("page " + (40));
  Button b41 = new Button("page " + (41));
  Button b42 = new Button("page " + (42));
  Button b43 = new Button("page " + (43));
  Button b44 = new Button("page " + (44));
  Button b45 = new Button("page " + (45));
  Button b46 = new Button("page " + (46));
  Button b47 = new Button("page " + (47));
  Button b48 = new Button("page " + (48));
  Button b49 = new Button("page " + (49));
  Button b50 = new Button("page " + (50));
  Button b51 = new Button("page " + (51));
  Button b52 = new Button("page " + (52));
  Button b53 = new Button("page " + (53));
  Button b54 = new Button("page " + (54));
  Button b55 = new Button("page " + (55));
  Button b56 = new Button("page " + (56));
  Button b57 = new Button("page " + (57));
  Button b58 = new Button("page " + (58));
  Button b59 = new Button("page " + (59));
  Button b60 = new Button("page " + (60));
  Button b61 = new Button("page " + (61));
  Button b62 = new Button("page " + (62));
  Button b63 = new Button("page " + (63));
  Label statusValueLabel = new Label("STOP" , Label.LEFT) ;
  Label timeValueLabel = new Label("0" , Label.LEFT) ;
  Label instructionValueLabel = new Label("NONE" , Label.LEFT) ;
  Label addressValueLabel = new Label("NULL" , Label.LEFT) ;
  Label pageFaultValueLabel = new Label("NO" , Label.LEFT) ;
  Label virtualPageValueLabel = new Label("x" , Label.LEFT) ;
  Label physicalPageValueLabel = new Label("0" , Label.LEFT) ;
  Label RValueLabel = new Label("0" , Label.LEFT) ;
  Label MValueLabel = new Label("0" , Label.LEFT) ;
  Label inMemTimeValueLabel = new Label("0" , Label.LEFT) ;
  Label lastTouchTimeValueLabel = new Label("0" , Label.LEFT) ;
  Label lowValueLabel = new Label("0" , Label.LEFT) ;
  Label highValueLabel = new Label("0" , Label.LEFT) ;
  Label l0 = new Label(null, Label.CENTER);
  Label l1 = new Label(null, Label.CENTER);
  Label l2 = new Label(null, Label.CENTER);
  Label l3 = new Label(null, Label.CENTER);
  Label l4 = new Label(null, Label.CENTER);
  Label l5 = new Label(null, Label.CENTER);
  Label l6 = new Label(null, Label.CENTER);
  Label l7 = new Label(null, Label.CENTER);
  Label l8 = new Label(null, Label.CENTER);
  Label l9 = new Label(null, Label.CENTER);
  Label l10 = new Label(null, Label.CENTER);
  Label l11 = new Label(null, Label.CENTER);
  Label l12 = new Label(null, Label.CENTER);
  Label l13 = new Label(null, Label.CENTER);
  Label l14 = new Label(null, Label.CENTER);
  Label l15 = new Label(null, Label.CENTER);
  Label l16 = new Label(null, Label.CENTER);
  Label l17 = new Label(null, Label.CENTER);
  Label l18 = new Label(null, Label.CENTER);
  Label l19 = new Label(null, Label.CENTER);
  Label l20 = new Label(null, Label.CENTER);
  Label l21 = new Label(null, Label.CENTER);
  Label l22 = new Label(null, Label.CENTER);
  Label l23 = new Label(null, Label.CENTER);
  Label l24 = new Label(null, Label.CENTER);
  Label l25 = new Label(null, Label.CENTER);
  Label l26 = new Label(null, Label.CENTER);
  Label l27 = new Label(null, Label.CENTER);
  Label l28 = new Label(null, Label.CENTER);
  Label l29 = new Label(null, Label.CENTER);
  Label l30 = new Label(null, Label.CENTER);
  Label l31 = new Label(null, Label.CENTER);
  Label l32 = new Label(null, Label.CENTER);
  Label l33 = new Label(null, Label.CENTER);
  Label l34 = new Label(null, Label.CENTER);
  Label l35 = new Label(null, Label.CENTER);
  Label l36 = new Label(null, Label.CENTER);
  Label l37 = new Label(null, Label.CENTER);
  Label l38 = new Label(null, Label.CENTER);
  Label l39 = new Label(null, Label.CENTER);
  Label l40 = new Label(null, Label.CENTER);
  Label l41 = new Label(null, Label.CENTER);
  Label l42 = new Label(null, Label.CENTER);
  Label l43 = new Label(null, Label.CENTER);
  Label l44 = new Label(null, Label.CENTER);
  Label l45 = new Label(null, Label.CENTER);
  Label l46 = new Label(null, Label.CENTER);
  Label l47 = new Label(null, Label.CENTER);
  Label l48 = new Label(null, Label.CENTER);
  Label l49 = new Label(null, Label.CENTER);
  Label l50 = new Label(null, Label.CENTER);
  Label l51 = new Label(null, Label.CENTER);
  Label l52 = new Label(null, Label.CENTER);
  Label l53 = new Label(null, Label.CENTER);
  Label l54 = new Label(null, Label.CENTER);
  Label l55 = new Label(null, Label.CENTER);
  Label l56 = new Label(null, Label.CENTER);
  Label l57 = new Label(null, Label.CENTER);
  Label l58 = new Label(null, Label.CENTER);
  Label l59 = new Label(null, Label.CENTER);
  Label l60 = new Label(null, Label.CENTER);
  Label l61 = new Label(null, Label.CENTER);
  Label l62 = new Label(null, Label.CENTER);
  Label l63 = new Label(null, Label.CENTER);

  public ControlPanel() 
  {
    super();
  }

  public ControlPanel( String title ) 
  {
    super(title);
  }

  public void init( Kernel useKernel , String commands , String config ) 
  {
    kernel = useKernel ;
    kernel.setControlPanel( this );
    setLayout( null );
    setBackground( Color.white );
    setForeground( Color.black );
    resize( 635 , 545 );
    setFont( new Font( "Courier", 0, 12 ) );   

    runButton.setForeground( Color.blue );
    runButton.setBackground( Color.lightGray );
    runButton.reshape( 0,25,70,15 );
    add( runButton );    

    stepButton.setForeground( Color.blue );
    stepButton.setBackground( Color.lightGray );
    stepButton.reshape( 70,25,70,15 );
    add( stepButton );

    resetButton.setForeground( Color.blue );
    resetButton.setBackground( Color.lightGray );
    resetButton.reshape( 140,25,70,15 );
    add( resetButton );

    exitButton.setForeground( Color.blue );
    exitButton.setBackground( Color.lightGray );
    exitButton.reshape( 210,25,70,15 );
    add( exitButton );

    b0.reshape(0, (0+2)*15+25, 70, 15);
    b0.setForeground( Color.magenta );
    b0.setBackground( Color.lightGray );
    add ( b0 );

    b1.reshape(0, (1+2)*15+25, 70, 15);
    b1.setForeground( Color.magenta );
    b1.setBackground( Color.lightGray );
    add ( b1 );
    
    b2.reshape(0, (2+2)*15+25, 70, 15);
    b2.setForeground( Color.magenta );
    b2.setBackground( Color.lightGray );
    add ( b2 );
    
    b3.reshape(0, (3+2)*15+25, 70, 15);
    b3.setForeground( Color.magenta );
    b3.setBackground( Color.lightGray );
    add ( b3 );
    
    b4.reshape(0, (4+2)*15+25, 70, 15);
    b4.setForeground( Color.magenta );
    b4.setBackground( Color.lightGray );
    add ( b4 );
    
    b5.reshape(0, (5+2)*15+25, 70, 15);
    b5.setForeground( Color.magenta );
    b5.setBackground( Color.lightGray );
    add ( b5 );
    
    b6.reshape(0, (6+2)*15+25, 70, 15);
    b6.setForeground( Color.magenta );
    b6.setBackground( Color.lightGray );
    add ( b6 );
    
    b7.reshape(0, (7+2)*15+25, 70, 15);
    b7.setForeground( Color.magenta );
    b7.setBackground( Color.lightGray );
    add ( b7 );
    
    b8.reshape(0, (8+2)*15+25, 70, 15);
    b8.setForeground( Color.magenta );
    b8.setBackground( Color.lightGray );
    add ( b8 );
    
    b9.reshape(0, (9+2)*15+25, 70, 15);
    b9.setForeground( Color.magenta );
    b9.setBackground( Color.lightGray );
    add ( b9 );
    
    b10.reshape(0, (10+2)*15+25, 70, 15);
    b10.setForeground( Color.magenta );
    b10.setBackground( Color.lightGray );
    add ( b10 );
    
    b11.reshape(0, (11+2)*15+25, 70, 15);
    b11.setForeground( Color.magenta );
    b11.setBackground( Color.lightGray );
    add ( b11 );
    
    b12.reshape(0, (12+2)*15+25, 70, 15);
    b12.setForeground( Color.magenta );
    b12.setBackground( Color.lightGray );
    add ( b12 );
    
    b13.reshape(0, (13+2)*15+25, 70, 15);
    b13.setForeground( Color.magenta );
    b13.setBackground( Color.lightGray );
    add ( b13 );
    
    b14.reshape(0, (14+2)*15+25, 70, 15);
    b14.setForeground( Color.magenta );
    b14.setBackground( Color.lightGray );
    add ( b14 );
    
    b15.reshape(0, (15+2)*15+25, 70, 15);
    b15.setForeground( Color.magenta );
    b15.setBackground( Color.lightGray );
    add ( b15 );
    
    b16.reshape(0, (16+2)*15+25, 70, 15);
    b16.setForeground( Color.magenta );
    b16.setBackground( Color.lightGray );
    add ( b16 );
    
    b17.reshape(0, (17+2)*15+25, 70, 15);
    b17.setForeground( Color.magenta );
    b17.setBackground( Color.lightGray );
    add ( b17 );
    
    b18.reshape(0, (18+2)*15+25, 70, 15);
    b18.setForeground( Color.magenta );
    b18.setBackground( Color.lightGray );
    add ( b18 );
    
    b19.reshape(0, (19+2)*15+25, 70, 15);
    b19.setForeground( Color.magenta );
    b19.setBackground( Color.lightGray );
    add ( b19 );
    
    b20.reshape(0, (20+2)*15+25, 70, 15);
    b20.setForeground( Color.magenta );
    b20.setBackground( Color.lightGray );
    add ( b20 );
    
    b21.reshape(0, (21+2)*15+25, 70, 15);
    b21.setForeground( Color.magenta );
    b21.setBackground( Color.lightGray );
    add ( b21 );
    
    b22.reshape(0, (22+2)*15+25, 70, 15);
    b22.setForeground( Color.magenta );
    b22.setBackground( Color.lightGray );
    add ( b22 );
    
    b23.reshape(0, (23+2)*15+25, 70, 15);
    b23.setForeground( Color.magenta );
    b23.setBackground( Color.lightGray );
    add ( b23 );
    
    b24.reshape(0, (24+2)*15+25, 70, 15);
    b24.setForeground( Color.magenta );
    b24.setBackground( Color.lightGray );
    add ( b24 );
    
    b25.reshape(0, (25+2)*15+25, 70, 15);
    b25.setForeground( Color.magenta );
    b25.setBackground( Color.lightGray );
    add ( b25 );
    
    b26.reshape(0, (26+2)*15+25, 70, 15);
    b26.setForeground( Color.magenta );
    b26.setBackground( Color.lightGray );
    add ( b26 );
    
    b27.reshape(0, (27+2)*15+25, 70, 15);
    b27.setForeground( Color.magenta );
    b27.setBackground( Color.lightGray );
    add ( b27 );
    
    b28.reshape(0, (28+2)*15+25, 70, 15);
    b28.setForeground( Color.magenta );
    b28.setBackground( Color.lightGray );
    add ( b28 );
    
    b29.reshape(0, (29+2)*15+25, 70, 15);
    b29.setForeground( Color.magenta );
    b29.setBackground( Color.lightGray );
    add ( b29 );
    
    b30.reshape(0, (30+2)*15+25, 70, 15);
    b30.setForeground( Color.magenta );
    b30.setBackground( Color.lightGray );
    add ( b30 );
    
    b31.reshape(0, (31+2)*15+25, 70, 15);
    b31.setForeground( Color.magenta );
    b31.setBackground( Color.lightGray );
    add ( b31 );
    
    b32.reshape(140, (0+2)*15+25, 70, 15);
    b32.setForeground( Color.magenta );
    b32.setBackground( Color.lightGray );
    add ( b32 );
    
    b33.reshape(140, (1+2)*15+25, 70, 15);
    b33.setForeground( Color.magenta );
    b33.setBackground( Color.lightGray );
    add ( b33 );
    
    b34.reshape(140, (2+2)*15+25, 70, 15);
    b34.setForeground( Color.magenta );
    b34.setBackground( Color.lightGray );
    add ( b34 );
    
    b35.reshape(140, (3+2)*15+25, 70, 15);
    b35.setForeground( Color.magenta );
    b35.setBackground( Color.lightGray );
    add ( b35 );
    
    b36.reshape(140, (4+2)*15+25, 70, 15);
    b36.setForeground( Color.magenta );
    b36.setBackground( Color.lightGray );
    add ( b36 );
    
    b37.reshape(140, (5+2)*15+25, 70, 15);
    b37.setForeground( Color.magenta );
    b37.setBackground( Color.lightGray );
    add ( b37 );
    
    b38.reshape(140, (6+2)*15+25, 70, 15);
    b38.setForeground( Color.magenta );
    b38.setBackground( Color.lightGray );
    add ( b38 );
    
    b39.reshape(140, (7+2)*15+25, 70, 15);
    b39.setForeground( Color.magenta );
    b39.setBackground( Color.lightGray );
    add ( b39 );

    b40.reshape(140, (8+2)*15+25, 70, 15);
    b40.setForeground( Color.magenta );
    b40.setBackground( Color.lightGray );
    add ( b40 );

    b41.reshape(140, (9+2)*15+25, 70, 15);
    b41.setForeground( Color.magenta );
    b41.setBackground( Color.lightGray );
    add ( b41 );

    b42.reshape(140, (10+2)*15+25, 70, 15);
    b42.setForeground( Color.magenta );
    b42.setBackground( Color.lightGray );
    add ( b42 );

    b43.reshape(140, (11+2)*15+25, 70, 15);
    b43.setForeground( Color.magenta );
    b43.setBackground( Color.lightGray );
    add ( b43 );

    b44.reshape(140, (12+2)*15+25, 70, 15);
    b44.setForeground( Color.magenta );
    b44.setBackground( Color.lightGray );
    add ( b44 );

    b45.reshape(140, (13+2)*15+25, 70, 15);
    b45.setForeground( Color.magenta );
    b45.setBackground( Color.lightGray );
    add ( b45 );

    b46.reshape(140, (14+2)*15+25, 70, 15);
    b46.setForeground( Color.magenta );
    b46.setBackground( Color.lightGray );
    add ( b46 );

    b47.reshape(140, (15+2)*15+25, 70, 15);
    b47.setForeground( Color.magenta );
    b47.setBackground( Color.lightGray );
    add ( b47 );

    b48.reshape(140, (16+2)*15+25, 70, 15);
    b48.setForeground( Color.magenta );
    b48.setBackground( Color.lightGray );
    add ( b48 );

    b49.reshape(140, (17+2)*15+25, 70, 15);
    b49.setForeground( Color.magenta );
    b49.setBackground( Color.lightGray );
    add ( b49 );

    b50.reshape(140, (18+2)*15+25, 70, 15);
    b50.setForeground( Color.magenta );
    b50.setBackground( Color.lightGray );
    add ( b50 );

    b51.reshape(140, (19+2)*15+25, 70, 15);
    b51.setForeground( Color.magenta );
    b51.setBackground( Color.lightGray );
    add ( b51 );

    b52.reshape(140, (20+2)*15+25, 70, 15);
    b52.setForeground( Color.magenta );
    b52.setBackground( Color.lightGray );
    add ( b52 );
    
    b53.reshape(140, (21+2)*15+25, 70, 15);
    b53.setForeground( Color.magenta );
    b53.setBackground( Color.lightGray );
    add ( b53 );
    
    b54.reshape(140, (22+2)*15+25, 70, 15);
    b54.setForeground( Color.magenta );
    b54.setBackground( Color.lightGray );
    add ( b54 );
    
    b55.reshape(140, (23+2)*15+25, 70, 15);
    b55.setForeground( Color.magenta );
    b55.setBackground( Color.lightGray );
    add ( b55 );

    b56.reshape(140, (24+2)*15+25, 70, 15);
    b56.setForeground( Color.magenta );
    b56.setBackground( Color.lightGray );
    add ( b56 );
    
    b57.reshape(140, (25+2)*15+25, 70, 15);
    b57.setForeground( Color.magenta );
    b57.setBackground( Color.lightGray );
    add ( b57 );
    
    b58.reshape(140, (26+2)*15+25, 70, 15);
    b58.setForeground( Color.magenta );
    b58.setBackground( Color.lightGray );
    add ( b58 );
    
    b59.reshape(140, (27+2)*15+25, 70, 15);
    b59.setForeground( Color.magenta );
    b59.setBackground( Color.lightGray );
    add ( b59 );

    b60.reshape(140, (28+2)*15+25, 70, 15);
    b60.setForeground( Color.magenta );
    b60.setBackground( Color.lightGray );
    add ( b60 );

    b61.reshape(140, (29+2)*15+25, 70, 15);
    b61.setForeground( Color.magenta );
    b61.setBackground( Color.lightGray );
    add ( b61 );

    b62.reshape(140, (30+2)*15+25, 70, 15);
    b62.setForeground( Color.magenta );
    b62.setBackground( Color.lightGray );
    add ( b62 );

    b63.reshape(140, (31+2)*15+25, 70, 15);
    b63.setForeground( Color.magenta );
    b63.setBackground( Color.lightGray );
    add ( b63 );

    statusValueLabel.reshape( 345,0+25,100,15 );
    add( statusValueLabel );

    timeValueLabel.reshape( 345,15+25,100,15 );
    add( timeValueLabel );

    instructionValueLabel.reshape( 385,45+25,100,15 );
    add( instructionValueLabel );

    addressValueLabel.reshape(385,60+25,230,15);
    add( addressValueLabel );

    pageFaultValueLabel.reshape( 385,90+25,100,15 );
    add( pageFaultValueLabel );

    virtualPageValueLabel.reshape( 395,120+25,200,15 );
    add( virtualPageValueLabel );

    physicalPageValueLabel.reshape( 395,135+25,200,15 );
    add( physicalPageValueLabel );

    RValueLabel.reshape( 395,150+25,200,15 );
    add( RValueLabel );

    MValueLabel.reshape( 395,165+25,200,15 );
    add( MValueLabel );

    inMemTimeValueLabel.reshape(395,180+25,200,15 );
    add( inMemTimeValueLabel );

    lastTouchTimeValueLabel.reshape( 395,195+25,200,15 );
    add( lastTouchTimeValueLabel );

    lowValueLabel.reshape( 395,210+25,230,15 );
    add( lowValueLabel );

    highValueLabel.reshape( 395,225+25,230,15 );
    add( highValueLabel );

    Label virtualOneLabel = new Label( "virtual" , Label.CENTER) ;
    virtualOneLabel.reshape(0,15+25,70,15); 
    add(virtualOneLabel);

    Label virtualTwoLabel = new Label( "virtual" , Label.CENTER) ;
    virtualTwoLabel.reshape(140,15+25,70,15); 
    add(virtualTwoLabel);

    Label physicalOneLabel = new Label( "physical" , Label.CENTER) ;
    physicalOneLabel.reshape(70,15+25,70,15); 
    add(physicalOneLabel);

    Label physicalTwoLabel = new Label( "physical" , Label.CENTER) ;
    physicalTwoLabel.reshape(210,15+25,70,15);
    add(physicalTwoLabel);

    Label statusLabel = new Label("status: " , Label.LEFT) ;
    statusLabel.reshape(285,0+25,65,15);
    add(statusLabel);

    Label timeLabel = new Label("time: " , Label.LEFT) ;
    timeLabel.reshape(285,15+25,50,15);
    add(timeLabel);

    Label instructionLabel = new Label("instruction: " , Label.LEFT) ;
    instructionLabel.reshape(285,45+25,100,15);
    add(instructionLabel);

    Label addressLabel = new Label("address: " , Label.LEFT) ;
    addressLabel.reshape(285,60+25,85,15);
    add(addressLabel);

    Label pageFaultLabel = new Label("page fault: " , Label.LEFT) ;
    pageFaultLabel.reshape(285,90+25,100,15);
    add(pageFaultLabel);

    Label virtualPageLabel = new Label("virtual page: " , Label.LEFT) ;
    virtualPageLabel.reshape(285,120+25,110,15);
    add(virtualPageLabel);

    Label physicalPageLabel = new Label("physical page: " , Label.LEFT) ;
    physicalPageLabel.reshape(285,135+25,110,15);
    add(physicalPageLabel);

    Label RLabel = new Label("R: ", Label.LEFT) ;
    RLabel.reshape(285,150+25,110,15);
    add(RLabel);

    Label MLabel = new Label("M: " , Label.LEFT) ;
    MLabel.reshape(285,165+25,110,15);
    add(MLabel);

    Label inMemTimeLabel = new Label("inMemTime: " , Label.LEFT) ;
    inMemTimeLabel.reshape(285,180+25,110,15);
    add(inMemTimeLabel);

    Label lastTouchTimeLabel = new Label("lastTouchTime: " , Label.LEFT) ;
    lastTouchTimeLabel.reshape(285,195+25,110,15);
    add(lastTouchTimeLabel);

    Label lowLabel = new Label("low: " , Label.LEFT) ;
    lowLabel.reshape(285,210+25,110,15);
    add(lowLabel);

    Label highLabel = new Label("high: " , Label.LEFT) ;
    highLabel.reshape(285,225+25,110,15);
    add(highLabel);

    l0.reshape( 70, (2)*15+25, 60, 15 );
    l0.setForeground( Color.red );
    l0.setFont( new Font( "Courier", 0, 10 ) );   
    add( l0 );

    l1.reshape( 70, (3)*15+25, 60, 15 );
    l1.setForeground( Color.red );
    l1.setFont( new Font( "Courier", 0, 10 ) );   
    add( l1 );

    l2.reshape( 70, (4)*15+25, 60, 15 );
    l2.setForeground( Color.red );
    l2.setFont( new Font( "Courier", 0, 10 ) );   
    add( l2 );

    l3.reshape( 70, (5)*15+25, 60, 15 );
    l3.setForeground( Color.red );
    l3.setFont( new Font( "Courier", 0, 10 ) );   
    add( l3 );

    l4.reshape( 70, (6)*15+25, 60, 15 );
    l4.setForeground( Color.red );
    l4.setFont( new Font( "Courier", 0, 10 ) );   
    add( l4 );

    l5.reshape( 70, (7)*15+25, 60, 15 );
    l5.setForeground( Color.red );
    l5.setFont( new Font( "Courier", 0, 10 ) );   
    add( l5 );

    l6.reshape( 70, (8)*15+25, 60, 15 );
    l6.setForeground( Color.red );
    l6.setFont( new Font( "Courier", 0, 10 ) );   
    add( l6 );

    l7.reshape( 70, (9)*15+25, 60, 15 );
    l7.setForeground( Color.red );
    l7.setFont( new Font( "Courier", 0, 10 ) );   
    add( l7 );

    l8.reshape( 70, (10)*15+25, 60, 15 );
    l8.setForeground( Color.red );
    l8.setFont( new Font( "Courier", 0, 10 ) );   
    add( l8 );

    l9.reshape( 70, (11)*15+25, 60, 15 );
    l9.setForeground( Color.red );
    l9.setFont( new Font( "Courier", 0, 10 ) );   
    add( l9 );

    l10.reshape( 70, (12)*15+25, 60, 15 );
    l10.setForeground( Color.red );
    l10.setFont( new Font( "Courier", 0, 10 ) );   
    add( l10 );

    l11.reshape( 70, (13)*15+25, 60, 15 );
    l11.setForeground( Color.red );
    l11.setFont( new Font( "Courier", 0, 10 ) );   
    add( l11 );

    l12.reshape( 70, (14)*15+25, 60, 15 );
    l12.setForeground( Color.red );
    l12.setFont( new Font( "Courier", 0, 10 ) );   
    add( l12 );

    l13.reshape( 70, (15)*15+25, 60, 15 );
    l13.setForeground( Color.red );
    l13.setFont( new Font( "Courier", 0, 10 ) );   
    add( l13 );

    l14.reshape( 70, (16)*15+25, 60, 15 );
    l14.setForeground( Color.red );
    l14.setFont( new Font( "Courier", 0, 10 ) );   
    add( l14 );

    l15.reshape( 70, (17)*15+25, 60, 15 );
    l15.setForeground( Color.red );
    l15.setFont( new Font( "Courier", 0, 10 ) );   
    add( l15 );

    l16.reshape( 70, (18)*15+25, 60, 15 );
    l16.setForeground( Color.red );
    l16.setFont( new Font( "Courier", 0, 10 ) );   
    add( l16 );

    l17.reshape( 70, (19)*15+25, 60, 15 );
    l17.setForeground( Color.red );
    l17.setFont( new Font( "Courier", 0, 10 ) );   
    add( l17 );

    l18.reshape( 70, (20)*15+25, 60, 15 );
    l18.setForeground( Color.red );
    l18.setFont( new Font( "Courier", 0, 10 ) );   
    add( l18 );

    l19.reshape( 70, (21)*15+25, 60, 15 );
    l19.setForeground( Color.red );
    l19.setFont( new Font( "Courier", 0, 10 ) );   
    add( l19 );

    l20.reshape( 70, (22)*15+25, 60, 15 );
    l20.setForeground( Color.red );
    l20.setFont( new Font( "Courier", 0, 10 ) );   
    add( l20 );

    l21.reshape( 70, (23)*15+25, 60, 15 );
    l21.setForeground( Color.red );
    l21.setFont( new Font( "Courier", 0, 10 ) );   
    add( l21 );

    l22.reshape( 70, (24)*15+25, 60, 15 );
    l22.setForeground( Color.red );
    l22.setFont( new Font( "Courier", 0, 10 ) );   
    add( l22 );

    l23.reshape( 70, (25)*15+25, 60, 15 );
    l23.setForeground( Color.red );
    l23.setFont( new Font( "Courier", 0, 10 ) );   
    add( l23 );

    l24.reshape( 70, (26)*15+25, 60, 15 );
    l24.setForeground( Color.red );
    l24.setFont( new Font( "Courier", 0, 10 ) );   
    add( l24 );

    l25.reshape( 70, (27)*15+25, 60, 15 );
    l25.setForeground( Color.red );
    l25.setFont( new Font( "Courier", 0, 10 ) );   
    add( l25 );

    l26.reshape( 70, (28)*15+25, 60, 15 );
    l26.setForeground( Color.red );
    l26.setFont( new Font( "Courier", 0, 10 ) );   
    add( l26 );

    l27.reshape( 70, (29)*15+25, 60, 15 );
    l27.setForeground( Color.red );
    l27.setFont( new Font( "Courier", 0, 10 ) );   
    add( l27 );

    l28.reshape( 70, (30)*15+25, 60, 15 );
    l28.setForeground( Color.red );
    l28.setFont( new Font( "Courier", 0, 10 ) );   
    add( l28 );

    l29.reshape( 70, (31)*15+25, 60, 15 );
    l29.setForeground( Color.red );
    l29.setFont( new Font( "Courier", 0, 10 ) );   
    add( l29 );

    l30.reshape( 70, (32)*15+25, 60, 15 );
    l30.setForeground( Color.red );
    l30.setFont( new Font( "Courier", 0, 10 ) );   
    add( l30 );

    l31.reshape( 70, (33)*15+25, 60, 15 );
    l31.setForeground( Color.red );
    l31.setFont( new Font( "Courier", 0, 10 ) );   
    add( l31 );

    l32.reshape( 210, (2)*15+25, 60, 15 );
    l32.setForeground( Color.red );
    l32.setFont( new Font( "Courier", 0, 10 ) );   
    add( l32 );

    l33.reshape( 210, (3)*15+25, 60, 15 );
    l33.setForeground( Color.red );
    l33.setFont( new Font( "Courier", 0, 10 ) );   
    add( l33 );

    l34.reshape( 210, (4)*15+25, 60, 15 );
    l34.setForeground( Color.red );
    l34.setFont( new Font( "Courier", 0, 10 ) );   
    add( l34 );

    l35.reshape( 210, (5)*15+25, 60, 15 );
    l35.setForeground( Color.red );
    l35.setFont( new Font( "Courier", 0, 10 ) );   
    add( l35 );

    l36.reshape( 210, (6)*15+25, 60, 15 );
    l36.setForeground( Color.red );
    l36.setFont( new Font( "Courier", 0, 10 ) );   
    add( l36 );

    l37.reshape( 210, (7)*15+25, 60, 15 );
    l37.setForeground( Color.red );
    l37.setFont( new Font( "Courier", 0, 10 ) );   
    add( l37 );

    l38.reshape( 210, (8)*15+25, 60, 15 );
    l38.setForeground( Color.red );
    l38.setFont( new Font( "Courier", 0, 10 ) );   
    add( l38 );

    l39.reshape( 210, (9)*15+25, 60, 15 );
    l39.setForeground( Color.red );
    l39.setFont( new Font( "Courier", 0, 10 ) );   
    add( l39 );

    l40.reshape( 210, (10)*15+25, 60, 15 );
    l40.setForeground( Color.red );
    l40.setFont( new Font( "Courier", 0, 10 ) );   
    add( l40 );

    l41.reshape( 210, (11)*15+25, 60, 15 );
    l41.setForeground( Color.red );
    l41.setFont( new Font( "Courier", 0, 10 ) );   
    add( l41 );

    l42.reshape( 210, (12)*15+25, 60, 15 );
    l42.setForeground( Color.red );
    l42.setFont( new Font( "Courier", 0, 10 ) );   
    add( l42 );

    l43.reshape( 210, (13)*15+25, 60, 15 );
    l43.setForeground( Color.red );
    l43.setFont( new Font( "Courier", 0, 10 ) );   
    add( l43 );

    l44.reshape( 210, (14)*15+25, 60, 15 );
    l44.setForeground( Color.red );
    l44.setFont( new Font( "Courier", 0, 10 ) );   
    add( l44 );

    l45.reshape( 210, (15)*15+25, 60, 15 );
    l45.setForeground( Color.red );
    l45.setFont( new Font( "Courier", 0, 10 ) );   
    add( l45 );

    l46.reshape( 210, (16)*15+25, 60, 15 );
    l46.setForeground( Color.red );
    l46.setFont( new Font( "Courier", 0, 10 ) );   
    add( l46 );

    l47.reshape( 210, (17)*15+25, 60, 15 );
    l47.setForeground( Color.red );
    l47.setFont( new Font( "Courier", 0, 10 ) );   
    add( l47 );

    l48.reshape( 210, (18)*15+25, 60, 15 );
    l48.setForeground( Color.red );
    l48.setFont( new Font( "Courier", 0, 10 ) );   
    add( l48 );

    l49.reshape( 210, (19)*15+25, 60, 15 );
    l49.setForeground( Color.red );
    l49.setFont( new Font( "Courier", 0, 10 ) );   
    add( l49 );

    l50.reshape( 210, (20)*15+25, 60, 15 );
    l50.setForeground( Color.red );
    l50.setFont( new Font( "Courier", 0, 10 ) );   
    add( l50 );

    l51.reshape( 210, (21)*15+25, 60, 15 );
    l51.setForeground( Color.red );
    l51.setFont( new Font( "Courier", 0, 10 ) );   
    add( l51 );

    l52.reshape( 210, (22)*15+25, 60, 15 );
    l52.setForeground( Color.red );
    l52.setFont( new Font( "Courier", 0, 10 ) );   
    add( l52 );

    l53.reshape( 210, (23)*15+25, 60, 15 );
    l53.setForeground( Color.red );
    l53.setFont( new Font( "Courier", 0, 10 ) );   
    add( l53 );

    l54.reshape( 210, (24)*15+25, 60, 15 );
    l54.setForeground( Color.red );
    l54.setFont( new Font( "Courier", 0, 10 ) );   
    add( l54 );

    l55.reshape( 210, (25)*15+25, 60, 15 );
    l55.setForeground( Color.red );
    l55.setFont( new Font( "Courier", 0, 10 ) );   
    add( l55 );

    l56.reshape( 210, (26)*15+25, 60, 15 );
    l56.setForeground( Color.red );
    l56.setFont( new Font( "Courier", 0, 10 ) );   
    add( l56 );

    l57.reshape( 210, (27)*15+25, 60, 15 );
    l57.setForeground( Color.red );
    l57.setFont( new Font( "Courier", 0, 10 ) );   
    add( l57 );

    l58.reshape( 210, (28)*15+25, 60, 15 );
    l58.setForeground( Color.red );
    l58.setFont( new Font( "Courier", 0, 10 ) );   
    add( l58 );

    l59.reshape( 210, (29)*15+25, 60, 15 );
    l59.setForeground( Color.red );
    l59.setFont( new Font( "Courier", 0, 10 ) );   
    add( l59 );

    l60.reshape( 210, (30)*15+25, 60, 15 );
    l60.setForeground( Color.red );
    l60.setFont( new Font( "Courier", 0, 10 ) );   
    add( l60 );

    l61.reshape( 210, (31)*15+25, 60, 15 );
    l61.setForeground( Color.red );
    l61.setFont( new Font( "Courier", 0, 10 ) );   
    add( l61 );

    l62.reshape( 210, (32)*15+25, 60, 15 );
    l62.setForeground( Color.red );
    l62.setFont( new Font( "Courier", 0, 10 ) );   
    add( l62 );


    l63.reshape( 210, (33)*15+25, 60, 15 );
    l63.setForeground( Color.red );
    l63.setFont( new Font( "Courier", 0, 10 ) );   
    add( l63 );

    kernel.init( commands , config );

    show();
  }

  public void paintPage( Page page ) 
  {
    virtualPageValueLabel.setText( Integer.toString( page.id ) );
    physicalPageValueLabel.setText( Integer.toString( page.physical ) );
    RValueLabel.setText( Integer.toString( page.R ) );
    MValueLabel.setText( Integer.toString( page.M ) );
    inMemTimeValueLabel.setText( Integer.toString( page.inMemTime ) );
    lastTouchTimeValueLabel.setText( Integer.toString( page.lastTouchTime ) );
    lowValueLabel.setText(Long.toString( page.low , Kernel.addressradix ) );
    highValueLabel.setText(Long.toString( page.high , Kernel.addressradix ) );
  }

  public void setStatus(String status) {
    statusValueLabel.setText(status);
  }

  public void addPhysicalPage( int pageNum , int physicalPage ) 
  {
    if ( physicalPage == 0 ) 
    {
      l0.setText( "page " + pageNum );
    }
    else if ( physicalPage == 1) 
    {
      l1.setText( "page " + pageNum );
    }
    else if ( physicalPage == 2)
    {
      l2.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 3)
    {
      l3.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 4)
    {
      l4.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 5)
    {
      l5.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 6)
    {
      l6.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 7)
    {
      l7.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 8)
    {
      l8.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 9)
    {
      l9.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 10)
    {
      l10.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 11)
    {
      l11.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 12)
    {
      l12.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 13)
    {
      l13.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 14)
    {
      l14.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 15)
    {
      l15.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 16)
    {
      l16.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 17)
    {
      l17.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 18)
    {
      l18.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 19)
    {
      l19.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 20)
    {
      l20.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 21)
    {
      l21.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 22)
    {
      l22.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 23)
    {
      l23.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 24)
    {
      l24.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 25)
    {
      l25.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 26)
    {
      l26.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 27)
    {
      l27.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 28)
    {
      l28.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 29)
    {
      l29.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 30)
    {
      l30.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 31)
    {
      l31.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 32)
    {
      l32.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 33)
    {
      l33.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 34)
    {
      l34.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 35)
    {
      l35.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 36)
    {
      l36.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 37)
    {
      l37.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 38)
    {
      l38.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 39)
    {
      l39.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 40)
    {
      l40.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 41)
    {
      l41.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 42)
    {
      l42.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 43)
    {
      l43.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 44)
    {
      l44.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 45)
    {
      l45.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 46)
    {
      l46.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 47)
    {
      l47.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 48)
    {
      l48.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 49)
    {
      l49.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 50)
    {
      l50.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 51)
    {
      l51.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 52)
    {
      l52.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 53)
    {
      l53.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 54)
    {
      l54.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 55)
    {
      l55.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 56)
    {
      l56.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 57)
    {
      l57.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 58)
    {
      l58.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 59)
    {
      l59.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 60)
    {
      l60.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 61)
    {
      l61.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 62)
    {
      l62.setText( "page " + pageNum );
    } 
    else if ( physicalPage == 63)
    {
      l63.setText( "page " + pageNum );
    } 
    else 
    {
      return;
    }
  }

  public void removePhysicalPage( int physicalPage ) 
  {
    if ( physicalPage == 0 ) 
    {
      l0.setText( null );
    }
    else if ( physicalPage == 1) 
    {
      l1.setText( null );
    }
    else if ( physicalPage == 2)
    {
      l2.setText(null  );
    } 
    else if ( physicalPage == 3)
    {
      l3.setText( null );
    } 
    else if ( physicalPage == 4)
    {
      l4.setText( null );
    } 
    else if ( physicalPage == 5)
    {
      l5.setText( null );
    } 
    else if ( physicalPage == 6)
    {
      l6.setText( null );
    } 
    else if ( physicalPage == 7)
    {
      l7.setText( null );
    } 
    else if ( physicalPage == 8)
    {
      l8.setText( null );
    } 
    else if ( physicalPage == 9)
    {
      l9.setText( null );
    } 
    else if ( physicalPage == 10)
    {
      l10.setText( null );
    } 
    else if ( physicalPage == 11)
    {
      l11.setText( null );
    } 
    else if ( physicalPage == 12)
    {
      l12.setText( null );
    } 
    else if ( physicalPage == 13)
    {
      l13.setText( null );
    } 
    else if ( physicalPage == 14)
    {
      l14.setText( null );
    } 
    else if ( physicalPage == 15)
    {
      l15.setText( null );
    } 
    else if ( physicalPage == 16)
    {
      l16.setText( null );
    } 
    else if ( physicalPage == 17)
    {
      l17.setText( null );
    } 
    else if ( physicalPage == 18)
    {
      l18.setText( null );
    } 
    else if ( physicalPage == 19)
    {
      l19.setText( null );
    } 
    else if ( physicalPage == 20)
    {
      l20.setText( null );
    } 
    else if ( physicalPage == 21)
    {
      l21.setText( null );
    } 
    else if ( physicalPage == 22)
    {
      l22.setText( null );
    } 
    else if ( physicalPage == 23)
    {
      l23.setText( null );
    } 
    else if ( physicalPage == 24)
    {
      l24.setText( null );
    } 
    else if ( physicalPage == 25)
    {
      l25.setText( null );
    } 
    else if ( physicalPage == 26)
    {
      l26.setText( null );
    } 
    else if ( physicalPage == 27)
    {
      l27.setText( null );
    } 
    else if ( physicalPage == 28)
    {
      l28.setText( null );
    } 
    else if ( physicalPage == 29)
    {
      l29.setText( null );
    } 
    else if ( physicalPage == 30)
    {
      l30.setText( null );
    } 
    else if ( physicalPage == 31)
    {
      l31.setText( null );
    } 
    else if ( physicalPage == 32)
    {
      l32.setText( null );
    } 
    else if ( physicalPage == 33)
    {
      l33.setText( null );
    } 
    else if ( physicalPage == 34)
    {
      l34.setText( null );
    } 
    else if ( physicalPage == 35)
    {
      l35.setText( null );
    } 
    else if ( physicalPage == 36)
    {
      l36.setText( null );
    } 
    else if ( physicalPage == 37)
    {
      l37.setText( null );
    } 
    else if ( physicalPage == 38)
    {
      l38.setText( null );
    } 
    else if ( physicalPage == 39)
    {
      l39.setText( null );
    } 
    else if ( physicalPage == 40)
    {
      l40.setText( null );
    } 
    else if ( physicalPage == 41)
    {
      l41.setText( null );
    } 
    else if ( physicalPage == 42)
    {
      l42.setText( null );
    } 
    else if ( physicalPage == 43)
    {
      l43.setText( null );
    } 
    else if ( physicalPage == 44)
    {
      l44.setText( null );
    } 
    else if ( physicalPage == 45)
    {
      l45.setText( null );
    } 
    else if ( physicalPage == 46)
    {
      l46.setText( null );
    } 
    else if ( physicalPage == 47)
    {
      l47.setText( null );
    } 
    else if ( physicalPage == 48)
    {
      l48.setText( null );
    } 
    else if ( physicalPage == 49)
    {
      l49.setText( null  );
    } 
    else if ( physicalPage == 50)
    {
      l50.setText( null );
    } 
    else if ( physicalPage == 51)
    {
      l51.setText( null );
    } 
    else if ( physicalPage == 52)
    {
      l52.setText( null );
    } 
    else if ( physicalPage == 53)
    {
      l53.setText( null );
    } 
    else if ( physicalPage == 54)
    {
      l54.setText( null );
    } 
    else if ( physicalPage == 55)
    {
      l55.setText( null );
    } 
    else if ( physicalPage == 56)
    {
      l56.setText( null );
    } 
    else if ( physicalPage == 57)
    {
      l57.setText( null );
    } 
    else if ( physicalPage == 58)
    {
      l58.setText( null );
    } 
    else if ( physicalPage == 59)
    {
      l59.setText( null );
    } 
    else if ( physicalPage == 60)
    {
      l60.setText( null );
    } 
    else if ( physicalPage == 61)
    {
      l61.setText( null );
    } 
    else if ( physicalPage == 62)
    {
      l62.setText( null );
    } 
    else if ( physicalPage == 63)
    {
      l63.setText( null );
    } 
    else 
    {
      return;
    }
  }


  public boolean action( Event e, Object arg )
  {
    if ( e.target == runButton ) 
    {
      setStatus( "RUN" );
      runButton.disable();
      stepButton.disable();
      resetButton.disable();
      kernel.run();
      setStatus( "STOP" );
      resetButton.enable();
      return true;
    } 
    else if ( e.target == stepButton )
    {
      setStatus( "STEP" );
      kernel.step();
      if (kernel.runcycles == kernel.runs) {
         stepButton.disable();
         runButton.disable();
      }
      setStatus("STOP");
      return true;
    }
    else if ( e.target == resetButton )
    {
      kernel.reset();
      runButton.enable();
      stepButton.enable();
      return true;
    }
    else if ( e.target == exitButton )
    {
      System.exit(0);
      return true;
    }
    else if ( e.target == b0 ) 
    {
      kernel.getPage(0);
      return true;
    }
    else if ( e.target == b1 ) 
    {
      kernel.getPage(1);
      return true;
    }
    else if ( e.target == b2 ) 
    {
      kernel.getPage(2);
      return true;
    }
    else if ( e.target == b3 ) 
    {
      kernel.getPage(3);
      return true;
    }
    else if ( e.target == b4 ) 
    {
      kernel.getPage(4);
      return true;
    }
    else if ( e.target == b5 ) 
    {
      kernel.getPage(5);
      return true;
    }
    else if ( e.target == b6 ) 
    {
      kernel.getPage(6);
      return true;
    }
    else if ( e.target == b7 ) 
    {
      kernel.getPage(7);
      return true;
    }
    else if ( e.target == b8 ) 
    {
      kernel.getPage(8);
      return true;
    }
    else if ( e.target == b9 ) 
    {
      kernel.getPage(9);
      return true;
    }
    else if ( e.target == b10 ) 
    {
      kernel.getPage(10);
      return true;
    }
    else if ( e.target == b11 ) 
    {
      kernel.getPage(11);
      return true;
    }
    else if ( e.target == b12 ) 
    {
      kernel.getPage(12);
      return true;
    }
    else if ( e.target == b13 ) 
    {
      kernel.getPage(13);
      return true;
    }
    else if ( e.target == b14 ) 
    {
      kernel.getPage(14);
      return true;
    }
    else if ( e.target == b15 ) 
    {
      kernel.getPage(15);
      return true;
    }
    else if ( e.target == b16 ) 
    {
      kernel.getPage(16);
      return true;
    }
    else if ( e.target == b17 ) 
    {
      kernel.getPage(17);
      return true;
    }
    else if ( e.target == b18 ) 
    {
      kernel.getPage(18);
      return true;
    }
    else if ( e.target == b19 ) 
    {
      kernel.getPage(19);
      return true;
    }
    else if ( e.target == b20 ) 
    {
      kernel.getPage(20);
      return true;
    }
    else if ( e.target == b21 ) 
    {
      kernel.getPage(21);
      return true;
    }
    else if ( e.target == b22 ) 
    {
      kernel.getPage(22);
      return true;
    }
    else if ( e.target == b23 ) 
    {
      kernel.getPage(23);
      return true;
    }
    else if ( e.target == b24 ) 
    {
      kernel.getPage(24);
      return true;
    }
    else if ( e.target == b25 ) 
    {
      kernel.getPage(25);
      return true;
    }
    else if ( e.target == b26 ) 
    {
      kernel.getPage(26);
      return true;
    }
    else if ( e.target == b27 ) 
    {
      kernel.getPage(27);
      return true;
    }
    else if ( e.target == b28 ) 
    {
      kernel.getPage(28);
      return true;
    }
    else if ( e.target == b29 ) 
    {
      kernel.getPage(29);
      return true;
    }
    else if ( e.target == b30 ) 
    {
      kernel.getPage(30);
      return true;
    }
    else if ( e.target == b31 ) 
    {
      kernel.getPage(31);
      return true;
    }
    else if ( e.target == b32 ) 
    {
      kernel.getPage(32);
      return true;
    }
    else if ( e.target == b33 ) 
    {
      kernel.getPage(33);
      return true;
    }
    else if ( e.target == b34 ) 
    {
      kernel.getPage(34);
      return true;
    }
    else if ( e.target == b35 ) 
    {
      kernel.getPage(35);
      return true;
    }
    else if ( e.target == b36 ) 
    {
      kernel.getPage(36);
      return true;
    }
    else if ( e.target == b37 ) 
    {
      kernel.getPage(37);
      return true;
    }
    else if ( e.target == b38 ) 
    {
      kernel.getPage(38);
      return true;
    }
    else if ( e.target == b39 ) 
    {
      kernel.getPage(39);
      return true;
    }
    else if ( e.target == b40 ) 
    {
      kernel.getPage(40);
      return true;
    }
    else if ( e.target == b41 ) 
    {
      kernel.getPage(41);
      return true;
    }
    else if ( e.target == b42 ) 
    {
      kernel.getPage(42);
      return true;
    }
    else if ( e.target == b43 ) 
    {
      kernel.getPage(43);
      return true;
    }
    else if ( e.target == b44 ) 
    {
      kernel.getPage(44);
      return true;
    }
    else if ( e.target == b45 ) 
    {
      kernel.getPage(45);
      return true;
    }
    else if ( e.target == b46 ) 
    {
      kernel.getPage(46);
      return true;
    }
    else if ( e.target == b47 ) 
    {
      kernel.getPage(47);
      return true;
    }
    else if ( e.target == b48 ) 
    {
      kernel.getPage(48);
      return true;
    }
    else if ( e.target == b49 ) 
    {
      kernel.getPage(49);
      return true;
    }
    else if ( e.target == b50 ) 
    {
      kernel.getPage(50);
      return true;
    }
    else if ( e.target == b51 ) 
    {
      kernel.getPage(51);
      return true;
    }
    else if ( e.target == b52 ) 
    {
      kernel.getPage(52);
      return true;
    }
    else if ( e.target == b53 ) 
    {
      kernel.getPage(53);
      return true;
    }
    else if ( e.target == b54 ) 
    {
      kernel.getPage(54);
      return true;
    }
    else if ( e.target == b55 ) 
    {
      kernel.getPage(55);
      return true;
    }
    else if ( e.target == b56 ) 
    {
      kernel.getPage(56);
      return true;
    }
    else if ( e.target == b57 ) 
    {
      kernel.getPage(57);
      return true;
    }
    else if ( e.target == b58 ) 
    {
      kernel.getPage(58);
      return true;
    }
    else if ( e.target == b59 ) 
    {
      kernel.getPage(59);
      return true;
    }
    else if ( e.target == b60 ) 
    {
      kernel.getPage(60);
      return true;
    }
    else if ( e.target == b61 ) 
    {
      kernel.getPage(61);
      return true;
    }
    else if ( e.target == b62 ) 
    {
      kernel.getPage(62);
      return true;
    }
    else if ( e.target == b63 ) 
    {
      kernel.getPage(63);
      return true;
    }
    else
    {
      return false;
    }
  }
}
