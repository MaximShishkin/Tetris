public class game {
	// Двухмерный массив для хранения игрового поля
		public int[][] mas = new int[30][20];		
		// Текущее направление движения и его флаг
		public int napr=0, fnapr;
		// Координаты головы змейки
		private int gX, gY;
		// Количество очков
		public int kol,kol_pol;
		// Признак окончания игры
		public boolean endg;
		// Проверка наличия внизу фигуры
		boolean fprov = true;
		// 
		public int color=0;
		
		public int[][] mass;
		public int[][] mas1={{1},{1},{1},{1},{1}};
		public int[][] mas2={{1,1,1},{1,0,1}};
		public int[][] mas3={{1,1},{1,0}};
		public int[][] mas4={{0,1,0},{1,1,1}};
		public int[][] mas5={{1,0,0},{1,1,1},{0,0,1}};
		public int[][] mas6={{1,1},{1,1},{1,1}};
		public int[][] mas7={{1,0},{1,1},{1,0},{1,0}};
		
		public int row;
		public int columns;
		
		// Генерация нового случайного объекта
		private void make_new()
		{
			// Рандомный выбор фигуры (номер)
			int numbermas=(int)(Math.random()*7);
			if (numbermas==0) mass=mas1;
			if (numbermas==1) mass=mas2;
			if (numbermas==2) mass=mas3;
			if (numbermas==3) mass=mas4;
			if (numbermas==4) mass=mas5;
			if (numbermas==5) mass=mas6;
			if (numbermas==6) mass=mas7;
			// Рандомный выбор номера цвета
			color = (int)(Math.random()*7);
			
			// Длина и высота фигуры
			row = mass.length;
			columns = mass[0].length;
			
			gX = 10;
		    gY = 0;
		    boolean flagproverki=true;
		    
		    //проверка на наличие фигуры в начале поля
		    for (int i = gY; i < row; i++) 
		    {
		    	for (int j = gX; j < gX+columns; j++) 
		    	{
					if (mas[i][j]!=0) flagproverki=false;
				}
		    }
		    
		    //добавление фигуры, если поле свободно или окончание игры
		    if (flagproverki)
			{
		    	for (int i = gY; i < row; i++) 
		    	{
					System.arraycopy(mass[i], 0, mas[i], gX, columns);
				}
			}
		    else endg=true;
		}
		
	//  Запуск игры (Старт)
		public void start()
		{
			// Заполняем весь массив нулями
			for (int i = 0; i < 30; i++) {
				for (int j = 0; j < 20; j++) {
					mas[i][j] = 0;
				}
			}
			//нижнюю строку заполняем -1
			/*for (int i = 0; i < 20; i++) {
				mas[29][i]=-1;
			}*/		
			// Количество очков
			kol = 0;
			// Создаем начальнй квадратик
			make_new();
			// Признак окончания игры
			endg=false;				
		}
		
		// Перемещение фигуры
		public boolean peremGolova()
		{	
			//обновление флага
			fprov=true;
			
			//если просто падает вниз
			if (napr == 0)
			{
				if ((gY + row) < 30)
				{
					// Проверяем есть ли рядом (снизу) фигуры
					for (int i=0; i<row; i++)
					{
						for (int j=0; j<columns; j++)
						{
							if ((mas[gY+i][gX+j]==1)&&(mas[gY+i+1][gX+j]>1)) fprov=false;  
						}
					}		
					if (fprov) gY++; 
				}
				else {gY=30-row; fprov=false;}
			}
			
			fnapr=0;
			// Если текущее направление влево
			if (napr == 3) 
			{
				boolean prov=true;
				if (gX - 1 >= 0)
				{
					// Проверяем есть ли рядом (слева) фигуры
					for (int i=0; i<row; i++)
					{
						for (int j=0; j<columns; j++)
						{
							if ((mas[gY+i][gX+j]==1)&&(mas[gY+i][gX+j-1]>1)) prov=false;  
						}
					}
					if (prov) gX--;
				}
			}			
			// Если текущее направление вправо
			if (napr == 2)
			{
				boolean prov=true;
				if (gX+columns <= 19)
				{
					// Проверяем есть ли рядом (справа) фигуры
					for (int i=0; i<row; i++)
					{
						for (int j=0; j<columns; j++)
						{
							if ((mas[gY+i][gX+j]==1)&&(mas[gY+i][gX+j+1]>1)) prov=false;  
						}
					}
					if (prov) gX++;
				}
			}
			//Если текущее направление вниз (ускорение)
			if (napr == 5)
			{
				boolean prov=true;
				if (gY<26-row)
				{
					// Проверяем есть ли рядом (снизу) фигуры
					for (int i=0; i<row; i++)
					{
						for (int j=0; j<columns; j++)
						{
							if ((mas[gY+i][gX+j]==1)&&((mas[gY+i+3][gX+j]>1)||(mas[gY+i+2][gX+j]>1)||(mas[gY+i+1][gX+j]>1))) prov=false;  
						}
					}		
					if (prov) gY+=3;
			    }
			 }
			// Если текущее направление вверх (поворот фигуры)
			if (napr == 1) 
			{
				boolean fpovorot = true;
				if ((gY<30-columns)&&(gX<20-row))
				{
					for (int i=gY; i<gY+columns; i++)
					{
						for (int j=gX; j<gX+row; j++)
						{
							if (mas[i][j]>1) fpovorot=false;
						}
					}
				}
				else fpovorot = false;
				
				// Создаем вспомогательный массив
				if (fpovorot)
				{
					int [][] masp =  new int[columns][row];
					// Транспонируем нашу фигуру и записываем результат во вспомогательный массив
					for (int i=0; i<row; i++)
					{
						for (int j=0; j<columns; j++)
						{
							masp[j][i] = mass[i][j];
						}
					}
					// Переписываем наш массив
					mass = masp;
					row = mass.length;
					columns = mass[0].length;
					// Зеркально отображаем массив по вертикали
					for (int i=0; i<row; i++)
					{
						for (int j=0; j<columns/2; j++)
						{
							//mass[i][j] = masp[i][columns-j-1];
							int t=mass[i][j];
							mass[i][j]=mass[i][columns-j-1];
							mass[i][columns-j-1]=t;
							
						}
					}
				}
			}
			napr=0;			
			return fprov;
			}
		
		public void perem() 
		{
			// Перемещаем квадр и получаем результат перемещения
			boolean flag = peremGolova();
			
			// Если перемещение в пустую ячейку (вниз)
			if (flag)
			{
				// Проверка всего массива и удаление фигуры из старого положения
				for (int i = 0; i < 30; i++) 
				{
					for (int j = 0; j < 20; j++) 
					{		
						 if (mas[i][j] == 1) {mas[i][j] = 0;}
					}
				}
				// Добавление фигуры в новое положение (добавляем только единицы)
				for (int i = gY; i < gY+row; i++) 
				{
					for (int j = gX; j < gX+columns; j++) 
					{		
						 if (mass[i-gY][j-gX]==1) mas[i][j] = mass[i-gY][j-gX];
					}
				}
			}
			// Конец поля или фигура 
			if (flag==false)
			{
				// Проверка всего массива и удаление фигуры из старого положения
				for (int i = 0; i < 30; i++) 
				{
					for (int j = 0; j < 20; j++) 
					{		
						 if (mas[i][j] == 1) {mas[i][j] = 0;}
					}
				}
				// Добавление фигуры в новое положение (добавляем только единицы)
				for (int i = gY; i < gY+row; i++) 
				{
					for (int j = gX; j < gX+columns; j++) 
					{		
						 if (mass[i-gY][j-gX]==1) mas[i][j] = mass[i-gY][j-gX];
					}
				}
				// Замена 1 на 5 (застывание фигуры)
				for (int i = 0; i < 30; i++) 
				{
					for (int j = 0; j < 20; j++) 
					{		
						 if (mas[i][j]==1) 
						 {
							 if (color==0) mas[i][j]=2;
							 if (color==1) mas[i][j]=3;
							 if (color==2) mas[i][j]=4;
							 if (color==3) mas[i][j]=5;
							 if (color==4) mas[i][j]=6;
							 if (color==5) mas[i][j]=7;
							 if (color==6) mas[i][j]=8; 
						 }
						 
					}
				}
			}
			
			// Проверка на заполнение строки
			if (flag==false) 
			{
				for (int i = 0; i <= 29; i++) 
				{
					kol_pol=0;
					for (int j = 0; j < 20; j++) 
					{
						if (mas[i][j]>1)
						{
							kol_pol=kol_pol+1;
						}
					}
					if(kol_pol==20)
					{
						for (int j = 0; j < 20; j++) 
						{
							for (int k = i; k>=1; k--)
							  {
								mas[k][j] = mas[k-1][j];
							  }
							mas[0][j]=0;
						}
						kol+=50;
					}
				}
				
				//Прибавляем счет +10
				kol+=10;
				//Создаем новую фигуру
				make_new();
			}
		}
}
