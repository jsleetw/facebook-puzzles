%Pi = ((A*Pi-1 + B) mod M) + 1 (for all i = 2..N)
%Wi = ((C*Wi-1 + D) mod K) + 1 (for all i = 2..N)

% 11 2 3 5 7 11 13 17 19



n = 11;
p1 = 2;
w1 = 3;
m = 5;
k = 7;
a = 11;
b = 13;
c = 17;
d = 19;
 
ps = [p1];
ws = [w1];

for i=2:n
  ps(i) = (mod((a*ps(i-1) + b) , m)) + 1;
  ws(i) = (mod((c*ws(i-1) + d) , k)) + 1;
end

ps

ws


plot(ws, ps, 'rx', 'MarkerSize', 5, 'MarkerFaceColor', 'y'); 
#plot(ps, 'ro', 'MarkerSize', 5, 'MarkerFaceColor', 'y'); 
ylabel('Weight');
xlabel('Price');
