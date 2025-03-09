[1mdiff --git a/nbproject/project.properties b/nbproject/project.properties[m
[1mindex ce9495e..8fcc6b9 100644[m
[1m--- a/nbproject/project.properties[m
[1m+++ b/nbproject/project.properties[m
[36m@@ -33,11 +33,14 @@[m [mdist.javadoc.dir=${dist.dir}/javadoc[m
 dist.jlink.dir=${dist.dir}/jlink[m
 dist.jlink.output=${dist.jlink.dir}/LocacaoDeCarros[m
 excludes=[m
[32m+[m[32mfile.reference.TimingFramework-0.55.jar=C:\\Users\\tlpof\\Downloads\\TimingFramework-0.55.jar[m
 includes=**[m
 jar.compress=false[m
[31m-javac.classpath=[m
[32m+[m[32mjavac.classpath=\[m
[32m+[m[32m    ${file.reference.TimingFramework-0.55.jar}:\[m
[32m+[m[32m    ${libs.absolutelayout.classpath}[m
 # Space-separated list of extra javac options[m
[31m-javac.compilerargs=[m
[32m+[m[32mjavac.compilerargs=\ --enable-preview[m
 javac.deprecation=false[m
 javac.external.vm=true[m
 javac.modulepath=[m
[36m@@ -71,7 +74,7 @@[m [mjlink.additionalmodules=[m
 jlink.additionalparam=[m
 jlink.launcher=true[m
 jlink.launcher.name=LocacaoDeCarros[m
[31m-main.class=gui.Teste[m
[32m+[m[32mmain.class=Main[m
 manifest.file=manifest.mf[m
 meta.inf.dir=${src.dir}/META-INF[m
 mkdist.disabled=false[m
[36m@@ -82,7 +85,7 @@[m [mrun.classpath=\[m
 # Space-separated list of JVM arguments used when running the project.[m
 # You may also define separate properties like run-sys-prop.name=value instead of -Dname=value.[m
 # To set system properties for unit tests define test-sys-prop.name=value:[m
[31m-run.jvmargs=[m
[32m+[m[32mrun.jvmargs=\ --enable-preview[m
 run.modulepath=\[m
     ${javac.modulepath}[m
 run.test.classpath=\[m
[1mdiff --git a/src/Main.java b/src/Main.java[m
[1mindex 5f08137..5115612 100644[m
[1m--- a/src/Main.java[m
[1m+++ b/src/Main.java[m
[36m@@ -2,10 +2,15 @@[m
 	import dados.RepoCarro;[m
 	import negocio.*;[m
 	import java.util.Set;[m
[32m+[m[32m        import negocio.Fachada;[m
[32m+[m[32m        import gui.menu.MenuOption;[m
 //==========================================[m
 public class Main {[m
 	public static void main(String[] args) {[m
[31m-		RepoCarro carros = RepoCarro.getInstance(); [m
[32m+[m[32m                Fachada fachada = new Fachada();[m
[32m+[m[32m                MenuOption tela = new MenuOption();[m
[32m+[m		[32mRepoCarro carros = fachada.get("carros");[m
[32m+[m[41m                [m
 [m
 		carros[m
 			.add("lamborguini", "huracan", 2024, "AAA-0002")[m
[36m@@ -19,5 +24,11 @@[m [mpublic class Main {[m
 		Carro carro             = carros.obj("placa","AAA-0000"); [m
 [m
 		my.print("%s ; %s ; %s ; %s %n", tamanho, conjuntoAll, conjunto, carro);[m
[32m+[m[41m                [m
[32m+[m[32m                java.awt.EventQueue.invokeLater(new Runnable() {[m
[32m+[m[32m            public void run() {[m
[32m+[m[32m                new MenuOption().setVisible(true);[m
[32m+[m[32m            }[m
[32m+[m[32m        });[m
 	}[m
 }[m
[1mdiff --git a/src/negocio/Aluguel.java b/src/negocio/Aluguel.java[m
[1mindex d9baf2d..e58b668 100644[m
[1m--- a/src/negocio/Aluguel.java[m
[1m+++ b/src/negocio/Aluguel.java[m
[36m@@ -9,7 +9,7 @@[m [mpublic class Aluguel extends LBox<Aluguel>{[m
 	double valor;[m
 	Cliente cliente;[m
 [m
[31m-	Aluguel(Cliente cliente, Carro carro, int dias){[m
[32m+[m	[32mpublic Aluguel(Cliente cliente, Carro carro, int dias){[m
 		localSet("dias", dias);[m
 		localSet("carro", carro);[m
 		localSet("cliente", cliente);[m
[1mdiff --git a/src/negocio/Carro.java b/src/negocio/Carro.java[m
[1mindex b43e62d..0249b8b 100644[m
[1m--- a/src/negocio/Carro.java[m
[1m+++ b/src/negocio/Carro.java[m
[36m@@ -29,7 +29,7 @@[m [mpublic class Carro extends LBox<Carro> {[m
 	}[m
 	[m
 // PROCESSOS DE ALUGUEL[m
[31m-	public String toString(){ return modelo+"/"+ano; }[m
[32m+[m	[32mpublic String toString(){ return marca+"/"+modelo+"/"+ano+"/"+placa+"/"+estado; }[m
 	public void setDisponivel(){ localSet("disponivel", true); }[m
 	public void setIndisponivel(){ localSet("disponivel", false); }[m
 [m
[1mdiff --git a/src/negocio/LBox.java b/src/negocio/LBox.java[m
[1mindex 314d581..020a4b9 100644[m
[1m--- a/src/negocio/LBox.java[m
[1m+++ b/src/negocio/LBox.java[m
[36m@@ -20,7 +20,7 @@[m [mpublic abstract class LBox<T> {[m
 	public static <T> void repo(LSetBook<T> repo){ reporef = repo; }[m
 [m
 	public <G> G get(String prop){[m
[31m-		return (getters.contains(prop)) ? localGet(prop) : null;[m
[32m+[m		[32mreturn (getters.contains(prop)) ? ((G) localGet(prop)) : null;[m
 	}[m
 	public <G> void set(String prop, G val){[m
 		if (setters.contains(prop)) { localSet(prop, val); } [m
