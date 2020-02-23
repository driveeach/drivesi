breed [people] ;; cria pessoas
people-own [ tempo-doente vida pegar? medo] ;; tempo-doente: tempo de demora da recuperação / vida: resistência da pessoa / pegar?: variável de controle
globals [ viva ] ;; vida: quantas pessoas vivas

;;=============================================================================================================================================

to setup
  clear-all
  ;; cria a quantidade de pessoas de acordo com essa variável
  create-people numero-de-pessoas
  ;; posiciona as pessoas de forma aleatória e da a elas a variável vida
  ask people
  [ setxy random-xcor random-ycor
    set color green
    set shape "person"
    set vida random-float resistencia
    set pegar? 1
    set medo random 5 ]
  ;; cria e atualiza o gráfico
  setup-plot
  update-plot
end

;;=============================================================================================================================================

;; quando clicado, infecta uma das pessoas verdes(saudáveis).
to infectar
  ask one-of people
   [ if color = green
    [ set color red ] ]
end

;; adiciona mais pessoas ao sistema.
to mais-pessoas
  create-people 20
  [ setxy random-xcor random-ycor
    set color green
    set shape "person"
    set vida random-float resistencia
    set pegar? 1 ]
end

;;=============================================================================================================================================

to go
  ;; se a pessoa estiver doente, ela recebe tempo-doente que se refere a potencia-da-doenca
  ask people [
  if ( color = red and pegar? = 1 )
  [ set tempo-doente potencia-da-doenca
    set pegar? 0 ] ]
  
  ;;ver abaixo
  cura
  
  ;; mesmo do comando acima.
  ask people [
  if ( color = red and pegar? = 1 )
  [ set tempo-doente potencia-da-doenca
    set pegar? 0 ] ]
  
  ask people
  [ ifelse desviar = false
    ;; quando desligado, as pessoas andam aleatoriamente
    [fd 1 right random 360]
    
    ;; quando ligado, as pessoas ficam com medo da doença e tendem a desviar dos doentes
    [ifelse color = yellow or color = green and (count people in-radius 3 with [color = red] + count people in-radius 2 with [color = yellow]) > medo
      
      [face one-of people in-radius 3 with [color = red or color = yellow]
        right 180 fd 2]
      
      [fd 1 right random 360] ]
    
    ;; executa doente para as pessoas vermelhas(doente?)
    if color = red [ doente? ]
    ;; liga ou desliga a transmição pelos agentes amarelos(doente?y)
    if amarela-transmite = true [
    if color = yellow [ doente?y ] ] ]
  
  ;; atualiza o gráfico
  update-plot
 end

;;=============================================================================================================================================

;; define a chance de uma pessoa se infectar.
to doente?
  ask people-here [
    if (random-float 100 <= chance-de-infectar) and color = green [
      set color red ] ]
end

;; define a chance de uma pessoa se infectar, caso ela esteja perto de uma ja imune.
to doente?y
  ask people-here [
    if (random-float 100 <= (chance-de-infectar / 20)) and color = green [
      set color red ] ]
end

;;=============================================================================================================================================

;; define como a pessoa pode se curar, ou morrer
to cura
  ask people
  [ if color = red
    [ if morrer? = true
    [ set vida (vida - random-float 0.1)
      if vida < 0
      [ die ] ]
      set tempo-doente (tempo-doente - random-float 0.5)
    if tempo-doente < 0
      [ set color yellow ] ] ]
end

;;=============================================================================================================================================

;; muta a doenca, fazendo com que as pessoas imunes voltem a ficar verdes, podendo pegar a doença agora mutada.
to mutar
  ask people
  [ if color = yellow
    [ set color green
      set pegar? 1 ] ]
end

;;=============================================================================================================================================

;; gráfico
to setup-plot
     set-current-plot "Estado das Pessoas"
  set-current-plot-pen "Saudaveis"
   plot count people with [ color = green ]
  set-current-plot-pen "Doentes"
   plot count people with [ color = red ]
  set-current-plot-pen "Imunes"
   plot count people with [ color = yellow ]
  set-current-plot-pen "total"
   plot count people
   
   set-current-plot "Resistencia"
   histogram [ int vida ] of people
end

;; atualizando o gráfico
to update-plot
     set-current-plot "Estado das Pessoas"
  set-current-plot-pen "Saudaveis"
   plot count people with [ color = green ]
  set-current-plot-pen "Doentes"
   plot count people with [ color = red ]
  set-current-plot-pen "Imunes"
   plot count people with [ color = yellow ]
  set-current-plot-pen "total"
   plot count people
   
   set-current-plot "Resistencia"
   histogram [ int vida ] of people
end

@#$#@#$#@
GRAPHICS-WINDOW
210
10
649
470
16
16
13.0
1
10
1
1
1
0
1
1
1
-16
16
-16
16
0
0
1
ticks

SLIDER
8
227
180
260
numero-de-pessoas
numero-de-pessoas
0
100
100
1
1
NIL
HORIZONTAL

BUTTON
8
10
71
43
NIL
setup
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL

BUTTON
80
10
154
43
NIL
infectar\n
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL

BUTTON
9
46
72
79
NIL
go
T
1
T
OBSERVER
NIL
NIL
NIL
NIL

BUTTON
80
47
155
80
go once
go
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL

SLIDER
9
102
181
135
Chance-de-Infectar
Chance-de-Infectar
0
100
28
1
1
NIL
HORIZONTAL

SLIDER
10
143
182
176
potencia-da-doenca
potencia-da-doenca
1
50
28
1
1
NIL
HORIZONTAL

SLIDER
8
187
180
220
resistencia
resistencia
1
100
41
1
1
NIL
HORIZONTAL

MONITOR
107
398
199
443
Num de pessoas
count people
17
1
11

SWITCH
9
266
112
299
morrer?
morrer?
0
1
-1000

PLOT
658
10
1004
262
Estado das Pessoas
Tempo
Quantidade
0.0
100.0
0.0
100.0
true
true
PENS
"Saudaveis" 1.0 0 -10899396 true
"Doentes" 1.0 0 -2674135 true
"Imunes" 1.0 0 -1184463 true
"total" 1.0 0 -16777216 true

MONITOR
9
345
127
390
Pessoas Saudaveis
count people with [ color = green ]
17
1
11

MONITOR
9
398
104
443
Pessoas Doentes
count people with [ color = red ]
17
1
11

PLOT
657
327
1004
471
Resistencia
NIL
NIL
0.0
100.0
0.0
10.0
true
false
PENS
"default" 1.0 1 -16777216 true

BUTTON
813
277
877
310
NIL
mutar
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL

SWITCH
114
266
206
299
desviar
desviar
0
1
-1000

BUTTON
665
277
796
310
Adicionar Pessoas
mais-pessoas
NIL
1
T
OBSERVER
NIL
NIL
NIL
NIL

SWITCH
9
305
165
338
amarela-transmite
amarela-transmite
0
1
-1000

@#$#@#$#@
WHAT IS IT?
-----------
Este programa simula os impactos de uma doenca desconhecida que atinge um grupo de pessoas pela primeira vez.


HOW IT WORKS
------------
Cada agente tem sua propria resistencia, baseando-se na potencia da doenca ela pode matar um grande numero de pessoas ou simplesmente ser erradicada.
As pessoas que ja haviam pego a doenca antes tendem a evitar o contato com as doentes ja que estas possuem um conhecimento previo dos sintomas da doenca.
As variaveis apresentadas possuem somente uma definicao MAXIMA, e nao valores estaticos.
O medo(variavel desviar) funciona da seguinte forma:
Cada pessoa tera um valor para medo, e quando o numero de pessoas doentes proximas a ela superar o valor de medo, ela tenta desviar deste grupo de pessoas(Tenta simular uma epidemia por exemplo)


HOW TO USE IT
-------------
Button Infectar: Infecta uma pessoa aleatoria, dentre aquelas que forem saudaveis.

Slider Chance-de-Infectar: Define a chance em % das pessoas contrairem a doenca quando em contato com uma pessoa infectada.

Slider Potencia-da-doenca: Define o quao perigosa eh a doenca.

Slider Resistencia: Define a resistencia de cada individuo quanto a sua capacidade de cura.

Slider Numero-de-pessoas: quantidade de pessoas a ser estudada.

Switch Morrer: Habilita ou nao a possibilidade da pessoa doente morrer.

Switch Desviar: Habilita ou nao o aprendizado quanto ao perigo de se contrair a doenca.

Swith Amarela-transmite: Habilita ou nao o fato dos agentes amarelos(imunes) poderem transmitir a doenca com uma chance menor.


THINGS TO NOTICE
----------------
O usuario, utilizando das variaveis, pode perceber as inumeras possibilidades de equilibrio do sistema apresentado. Como cada pessoa tem suas caracteristicas proprias dentro deste sistema (assim como ocorre na vida real), as vezes o sistema pode se equilibrar muito rapido. Ate mesmo perceber que todas as pessoas podem morrer no sistema.


THINGS TO TRY
-------------
Utilize das variaveis do programa para tentar deixar o maximo de pessoas saudaveis.


EXTENDING THE MODEL
-------------------
Aumentar a variabilidade da doenca, ou a interatividade entre os agentes, como medo.


NETLOGO FEATURES
----------------
This section could point out any especially interesting or unusual features of NetLogo that the model makes use of, particularly in the Procedures tab.  It might also point out places where workarounds were needed because of missing features.


RELATED MODELS
--------------
Na area de biologia do NetLogo Library, temos o problema com VIRUS e o da AIDS, que sao problemas relacionados a doencas no NetLogo

ULTIMA IMPLEMENTACAO (EXTRA)
--------------
Medo: Cada pessoa tem um valor para medo, o que significa que, se no raio de visao dela, o numero de pessoas doentes mais o numero de pessoas ja imunes superar o valor de medo dela, ela tentara desviar destas pessoas.

Swith Transmicao Amarela: Liga ou desliga a transmicao pelos agentes amarelos.

Transmicao amarela: As pessoas ja imunes da doenca tem uma chance menor de transmitirem a doenca para aquelas ainda saudaveis.


CREDITS AND REFERENCES
----------------------
Nomes:
Ivan Krunoslav Suto        7557251
Guilherme Venancio Borba   7556931
Gustavo Henrique Moreno    7556945
Eduardo Barbosa Barros     7557456
@#$#@#$#@
default
true
0
Polygon -7500403 true true 150 5 40 250 150 205 260 250

airplane
true
0
Polygon -7500403 true true 150 0 135 15 120 60 120 105 15 165 15 195 120 180 135 240 105 270 120 285 150 270 180 285 210 270 165 240 180 180 285 195 285 165 180 105 180 60 165 15

arrow
true
0
Polygon -7500403 true true 150 0 0 150 105 150 105 293 195 293 195 150 300 150

box
false
0
Polygon -7500403 true true 150 285 285 225 285 75 150 135
Polygon -7500403 true true 150 135 15 75 150 15 285 75
Polygon -7500403 true true 15 75 15 225 150 285 150 135
Line -16777216 false 150 285 150 135
Line -16777216 false 150 135 15 75
Line -16777216 false 150 135 285 75

bug
true
0
Circle -7500403 true true 96 182 108
Circle -7500403 true true 110 127 80
Circle -7500403 true true 110 75 80
Line -7500403 true 150 100 80 30
Line -7500403 true 150 100 220 30

butterfly
true
0
Polygon -7500403 true true 150 165 209 199 225 225 225 255 195 270 165 255 150 240
Polygon -7500403 true true 150 165 89 198 75 225 75 255 105 270 135 255 150 240
Polygon -7500403 true true 139 148 100 105 55 90 25 90 10 105 10 135 25 180 40 195 85 194 139 163
Polygon -7500403 true true 162 150 200 105 245 90 275 90 290 105 290 135 275 180 260 195 215 195 162 165
Polygon -16777216 true false 150 255 135 225 120 150 135 120 150 105 165 120 180 150 165 225
Circle -16777216 true false 135 90 30
Line -16777216 false 150 105 195 60
Line -16777216 false 150 105 105 60

car
false
0
Polygon -7500403 true true 300 180 279 164 261 144 240 135 226 132 213 106 203 84 185 63 159 50 135 50 75 60 0 150 0 165 0 225 300 225 300 180
Circle -16777216 true false 180 180 90
Circle -16777216 true false 30 180 90
Polygon -16777216 true false 162 80 132 78 134 135 209 135 194 105 189 96 180 89
Circle -7500403 true true 47 195 58
Circle -7500403 true true 195 195 58

circle
false
0
Circle -7500403 true true 0 0 300

circle 2
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240

cow
false
0
Polygon -7500403 true true 200 193 197 249 179 249 177 196 166 187 140 189 93 191 78 179 72 211 49 209 48 181 37 149 25 120 25 89 45 72 103 84 179 75 198 76 252 64 272 81 293 103 285 121 255 121 242 118 224 167
Polygon -7500403 true true 73 210 86 251 62 249 48 208
Polygon -7500403 true true 25 114 16 195 9 204 23 213 25 200 39 123

cylinder
false
0
Circle -7500403 true true 0 0 300

dot
false
0
Circle -7500403 true true 90 90 120

face happy
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 255 90 239 62 213 47 191 67 179 90 203 109 218 150 225 192 218 210 203 227 181 251 194 236 217 212 240

face neutral
false
0
Circle -7500403 true true 8 7 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Rectangle -16777216 true false 60 195 240 225

face sad
false
0
Circle -7500403 true true 8 8 285
Circle -16777216 true false 60 75 60
Circle -16777216 true false 180 75 60
Polygon -16777216 true false 150 168 90 184 62 210 47 232 67 244 90 220 109 205 150 198 192 205 210 220 227 242 251 229 236 206 212 183

fish
false
0
Polygon -1 true false 44 131 21 87 15 86 0 120 15 150 0 180 13 214 20 212 45 166
Polygon -1 true false 135 195 119 235 95 218 76 210 46 204 60 165
Polygon -1 true false 75 45 83 77 71 103 86 114 166 78 135 60
Polygon -7500403 true true 30 136 151 77 226 81 280 119 292 146 292 160 287 170 270 195 195 210 151 212 30 166
Circle -16777216 true false 215 106 30

flag
false
0
Rectangle -7500403 true true 60 15 75 300
Polygon -7500403 true true 90 150 270 90 90 30
Line -7500403 true 75 135 90 135
Line -7500403 true 75 45 90 45

flower
false
0
Polygon -10899396 true false 135 120 165 165 180 210 180 240 150 300 165 300 195 240 195 195 165 135
Circle -7500403 true true 85 132 38
Circle -7500403 true true 130 147 38
Circle -7500403 true true 192 85 38
Circle -7500403 true true 85 40 38
Circle -7500403 true true 177 40 38
Circle -7500403 true true 177 132 38
Circle -7500403 true true 70 85 38
Circle -7500403 true true 130 25 38
Circle -7500403 true true 96 51 108
Circle -16777216 true false 113 68 74
Polygon -10899396 true false 189 233 219 188 249 173 279 188 234 218
Polygon -10899396 true false 180 255 150 210 105 210 75 240 135 240

house
false
0
Rectangle -7500403 true true 45 120 255 285
Rectangle -16777216 true false 120 210 180 285
Polygon -7500403 true true 15 120 150 15 285 120
Line -16777216 false 30 120 270 120

leaf
false
0
Polygon -7500403 true true 150 210 135 195 120 210 60 210 30 195 60 180 60 165 15 135 30 120 15 105 40 104 45 90 60 90 90 105 105 120 120 120 105 60 120 60 135 30 150 15 165 30 180 60 195 60 180 120 195 120 210 105 240 90 255 90 263 104 285 105 270 120 285 135 240 165 240 180 270 195 240 210 180 210 165 195
Polygon -7500403 true true 135 195 135 240 120 255 105 255 105 285 135 285 165 240 165 195

line
true
0
Line -7500403 true 150 0 150 300

line half
true
0
Line -7500403 true 150 0 150 150

pentagon
false
0
Polygon -7500403 true true 150 15 15 120 60 285 240 285 285 120

person
false
0
Circle -7500403 true true 110 5 80
Polygon -7500403 true true 105 90 120 195 90 285 105 300 135 300 150 225 165 300 195 300 210 285 180 195 195 90
Rectangle -7500403 true true 127 79 172 94
Polygon -7500403 true true 195 90 240 150 225 180 165 105
Polygon -7500403 true true 105 90 60 150 75 180 135 105

plant
false
0
Rectangle -7500403 true true 135 90 165 300
Polygon -7500403 true true 135 255 90 210 45 195 75 255 135 285
Polygon -7500403 true true 165 255 210 210 255 195 225 255 165 285
Polygon -7500403 true true 135 180 90 135 45 120 75 180 135 210
Polygon -7500403 true true 165 180 165 210 225 180 255 120 210 135
Polygon -7500403 true true 135 105 90 60 45 45 75 105 135 135
Polygon -7500403 true true 165 105 165 135 225 105 255 45 210 60
Polygon -7500403 true true 135 90 120 45 150 15 180 45 165 90

sheep
false
0
Rectangle -7500403 true true 151 225 180 285
Rectangle -7500403 true true 47 225 75 285
Rectangle -7500403 true true 15 75 210 225
Circle -7500403 true true 135 75 150
Circle -16777216 true false 165 76 116

square
false
0
Rectangle -7500403 true true 30 30 270 270

square 2
false
0
Rectangle -7500403 true true 30 30 270 270
Rectangle -16777216 true false 60 60 240 240

star
false
0
Polygon -7500403 true true 151 1 185 108 298 108 207 175 242 282 151 216 59 282 94 175 3 108 116 108

target
false
0
Circle -7500403 true true 0 0 300
Circle -16777216 true false 30 30 240
Circle -7500403 true true 60 60 180
Circle -16777216 true false 90 90 120
Circle -7500403 true true 120 120 60

tree
false
0
Circle -7500403 true true 118 3 94
Rectangle -6459832 true false 120 195 180 300
Circle -7500403 true true 65 21 108
Circle -7500403 true true 116 41 127
Circle -7500403 true true 45 90 120
Circle -7500403 true true 104 74 152

triangle
false
0
Polygon -7500403 true true 150 30 15 255 285 255

triangle 2
false
0
Polygon -7500403 true true 150 30 15 255 285 255
Polygon -16777216 true false 151 99 225 223 75 224

truck
false
0
Rectangle -7500403 true true 4 45 195 187
Polygon -7500403 true true 296 193 296 150 259 134 244 104 208 104 207 194
Rectangle -1 true false 195 60 195 105
Polygon -16777216 true false 238 112 252 141 219 141 218 112
Circle -16777216 true false 234 174 42
Rectangle -7500403 true true 181 185 214 194
Circle -16777216 true false 144 174 42
Circle -16777216 true false 24 174 42
Circle -7500403 false true 24 174 42
Circle -7500403 false true 144 174 42
Circle -7500403 false true 234 174 42

turtle
true
0
Polygon -10899396 true false 215 204 240 233 246 254 228 266 215 252 193 210
Polygon -10899396 true false 195 90 225 75 245 75 260 89 269 108 261 124 240 105 225 105 210 105
Polygon -10899396 true false 105 90 75 75 55 75 40 89 31 108 39 124 60 105 75 105 90 105
Polygon -10899396 true false 132 85 134 64 107 51 108 17 150 2 192 18 192 52 169 65 172 87
Polygon -10899396 true false 85 204 60 233 54 254 72 266 85 252 107 210
Polygon -7500403 true true 119 75 179 75 209 101 224 135 220 225 175 261 128 261 81 224 74 135 88 99

wheel
false
0
Circle -7500403 true true 3 3 294
Circle -16777216 true false 30 30 240
Line -7500403 true 150 285 150 15
Line -7500403 true 15 150 285 150
Circle -7500403 true true 120 120 60
Line -7500403 true 216 40 79 269
Line -7500403 true 40 84 269 221
Line -7500403 true 40 216 269 79
Line -7500403 true 84 40 221 269

x
false
0
Polygon -7500403 true true 270 75 225 30 30 225 75 270
Polygon -7500403 true true 30 75 75 30 270 225 225 270

@#$#@#$#@
NetLogo 4.1.3
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
@#$#@#$#@
default
0.0
-0.2 0 1.0 0.0
0.0 1 1.0 0.0
0.2 0 1.0 0.0
link direction
true
0
Line -7500403 true 150 150 90 180
Line -7500403 true 150 150 210 180

@#$#@#$#@
0
@#$#@#$#@
