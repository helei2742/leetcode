import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.PriorityQueue;

public class dijekesitra {
    public dijekesitra(int n){
        for (int i = 0; i < MAXN; i++) {
            edge[i] = new Edge();
        }
        this.n = n;
    }
    private final int MAXN = 100005;
    class Edge{
        int to;
        int value;
        int next;
    }
    private Edge[] edge = new Edge[MAXN*2];
    private int[] head = new int[MAXN];
    private int tot = 0;

    private int[] dis = new int[MAXN];
    private boolean[] isValid = new boolean[MAXN];

    public void add(int u, int v, int w){
        tot++;
        edge[tot].to = v;
        edge[tot].value = w;
        edge[tot].next = head[u];
        head[u] = tot;
    }

    private int n;
    private final int INF = Integer.MAX_VALUE - 10;

    public void dijkstra(int start) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 1; i <= n; i++) {
            dis[i] = INF;
            isValid[i] = true;
        }
        dis[start] = 0;
        queue.add(new int[]{0, start});

        while(!queue.isEmpty()){
            int[] front = queue.poll();

            int now = front[1];

            if(!isValid[now]) continue;
            isValid[now] = false;

            for (int i = head[now]; i != 0; i = edge[i].next) {

                int next = edge[i].to;
                int v = edge[i].value;

                if(dis[next] > dis[now] + v){
                    dis[next] = dis[now] + v;
                    if(isValid[next]){
                        queue.add(new int[]{dis[next], next});
                    }
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            System.out.println(dis[i]);
        }
    }

}
