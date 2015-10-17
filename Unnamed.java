package boubouworld2;

public class Unnamed
{
public static void main(String[] args)
{
int num = 7;
int numDif = num*(num-1)/2;

Particle[] balls = new Particle[num];
for(int i = 0; i < num; i++)
{
    balls[i] = new Particle(1);
}

Vector[] differences = new Vector[numDif];
for(int i = 0; i < numDif; i++)
{
    differences[i] = new Vector();
}

int k = 0;

for(int i = 0; i < num-1 ; i++)
{
    for(int j = num-1; j > i; j--)
    {
	differences[k] = balls[i].motion.velocity.substractVector(balls[j].motion.velocity);
	    System.out.println((k+1) + " : " + differences[k]);
	k ++;
	
    }

}
}
}
