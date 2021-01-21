
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.*;

public class pole {

	public static void main(String[] args) {
		
		myFrame okno = new myFrame();
	}
}
	
	class myFrame extends JFrame
	{
		public myFrame()
		{
			// Создание объекта панели и подключения ее к окну
			myPanel p = new myPanel();
			Container cont = getContentPane();
			cont.add(p);
			// Заголовок окна
			setTitle("Tetris");
			// Границы окна: расположение и размеры
			setBounds(0,0,1000,700);
			// При закрытии окна - завершение приложения
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// Запрет изменения размеров окна
			setResizable(false);
			// Отображение (показ) окна
			setVisible(true);
		}
	}
	
	class myPanel extends JPanel
	{
		 // Переменная для реализации логики игры
		 private game myGame;	 
		 // Два таймера: отрисовки и изменения логики игры
		 private Timer tmDraw, tmUpdate;	 
		 // Изображения, используемые в игре
		 public Image color1,color2,color3,color4,color5,color6,color7,fon,endg;	 
		 // Надпись для количества очков
		 private JLabel lb;	 
		 // Две кнопки
		 private JButton btn1,btn2;	
		 // Ссылка на панель
		 private myPanel pan;
		 private class myKey implements KeyListener  
		 {
			 // Метод при нажатии на клавишу
			 public void keyPressed(KeyEvent e)
			 {
				// Получение кода нажатой клавиши
			   	int key = e.getKeyCode();	
			   	
			   	// Если нажатие одной из четырех стрелочек, то изменение направления квадр
			   	if (key==KeyEvent.VK_LEFT) myGame. napr = 3;
			   	else if (key==KeyEvent.VK_UP) myGame. napr = 1;
			   	else if (key==KeyEvent.VK_RIGHT) myGame. napr = 2;
			   	else if (key==KeyEvent.VK_DOWN) myGame. napr = 5;
			 }
			 public void keyReleased(KeyEvent e) {}
			 public void keyTyped(KeyEvent e) {}
		 }  
		// Конструктор класса 
			public myPanel()
			{
		 	  // Помещаем ссылку на саму панель в переменную
			      pan = this;
				
				// Подключение обработчика события для клавиатуры к панели
			      this.addKeyListener(new myKey());		  
			      // Делаем панель в фокусе - для приема событий от клавиатуры	
		         this.setFocusable(true);           
							
		       // Попытка загрузки всех изображений для игры
		       try
		       {
		      	 color1 = ImageIO.read(this.getClass().getResource("1.png"));
		      	 color2 = ImageIO.read(this.getClass().getResource("2.png"));
		      	 color3 = ImageIO.read(this.getClass().getResource("3.png"));
		      	 color4 = ImageIO.read(this.getClass().getResource("4.png"));
		      	 color5 = ImageIO.read(this.getClass().getResource("5.png"));
		      	 color6 = ImageIO.read(this.getClass().getResource("6.png"));
		      	 color7 = ImageIO.read(this.getClass().getResource("7.png"));
		      	 endg = ImageIO.read(this.getClass().getResource("endg.png")); 
		      	 fon = ImageIO.read(this.getClass().getResource("fon.png"));
		       }
		       catch (Exception ex) {}
		       
		       // Создаем объект новой игры
		       myGame = new game();
		       myGame.start();
		                 
		       // Создаем, настраиваем и запускаем таймер 
		       // для отрисовки игрового поля  
		       tmDraw = new Timer(20,new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// Вызываем перерисовку - paintComponent()
						repaint();
					}
				});          
		       tmDraw.start();
		       
		       // Создаем, настраиваем и запускаем таймер 
		       // для изменения логики игры          
		       tmUpdate = new Timer(100,new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// Если не конец игры, то перемещаем змейку
						if (myGame.endg==false)
						{
						  myGame.perem();
						}				
						// Выводим информацию о количестве очков
						lb.setText("Счет: "+myGame.kol);				
					}
				});
		       tmUpdate.start();
		       
		       // Включаем возможность произвольного размещения
		       // элементов интерфейса на панели
		       setLayout(null);
		       
		       // Создаем текстовую надпись
		       lb = new JLabel("Счет: "+myGame.kol);
		       lb.setForeground(Color.BLACK);
		       lb.setFont(new Font("serif",0,40));
		       lb.setBounds(500, 150, 200, 100);
		       add(lb);
		       
		       // Создаем кнопку Новая игра          
		       btn1 = new JButton();
		       btn1.setText("Новая игра");
		       btn1.setForeground(Color.BLUE);
		       btn1.setFont(new Font("serif",0,20));
		       btn1.setBounds(700, 70, 200, 100);
		       btn1.addActionListener(new ActionListener() {
		     	// Обработчик события при нажатии на кнопку Новая игра
					public void actionPerformed(ActionEvent arg0) {
						// Запуск игры
						myGame.start();
						// Забираем фокус у кнопки Новая игры
						btn1.setFocusable(false);
						// Забираем фокус у кнопки Выход				
						btn2.setFocusable(false);
						// Отдаем фокус панели
						pan.setFocusable(true);				
					}
				});
		       add(btn1);
		       
		       // Создаем кнопку Выход
		       btn2 = new JButton();
		       btn2.setText("Выход");
		       btn2.setForeground(Color.RED);
		       btn2.setFont(new Font("serif",0,20));
		       btn2.setBounds(700, 220, 200, 100);
		       btn2.addActionListener(new ActionListener() {
		       	// Обработчик события при нажатии на кнопку Новая игра        	  
					public void actionPerformed(ActionEvent arg0) {
					// Выход их игры - завершение работы приложения
						System.exit(0);				
					}
				});
		       add(btn2);
		                 
			}

			// Метод отрисовки
			public void paintComponent(Graphics gr)
			{			
				// Очищение игрового поля
				super.paintComponent(gr);
						
				// Отрисовка фона
				gr.drawImage(fon,0,0,1000,750,null);
												
				// Отрисовка игрового поля на основании массива
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 20; j++) {
						if (myGame.mas[i][j]!=0)
						{
						   if (myGame.mas[i][j]==1)
						   {
							   // Выводим картинку выбранного цвета в ячеку
							   if (myGame.color==0) gr.drawImage(color1,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==1) gr.drawImage(color2,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==2) gr.drawImage(color3,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==3) gr.drawImage(color4,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==4) gr.drawImage(color5,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==5) gr.drawImage(color6,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==6) gr.drawImage(color7,20+j*20, 10+i*20,20,20,null);
						   }
						// Выводим картинки выбранного цвета в ячейку
						   else if (myGame.mas[i][j]==2) gr.drawImage(color1,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==3) gr.drawImage(color2,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==4) gr.drawImage(color3,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==5) gr.drawImage(color4,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==6) gr.drawImage(color5,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==7) gr.drawImage(color6,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==8) gr.drawImage(color7,20+j*20, 10+i*20,20,20,null);
						}
						//gr.setColor(Color.YELLOW);
						//gr.setFont(new Font("arial",0,22));
						//gr.drawString(""+myGame.mas[i][j], 12+j*20, 30+i*20);
					}
					
				}
				
				
				// Отрисовка сетки игрового поля из линий
				gr.setColor(Color.gray);		
				for (int i = 0; i <= 20; i++)
				{
					for (int j = 0; j <= 30; j++)
					{
					// Рисование линий сетки
					gr.drawLine(20+i*20, 10, 20+i*20, 610);
					gr.drawLine(20, 10+j*20, 420, 10+j*20);			
					}
				}	
				
				// Вывод изображения конца игры - при окончании игры
				
				if (myGame.endg==true)
				{
					gr.drawImage(endg,250,300,400,200,null);
				}
				
			}	
	}

