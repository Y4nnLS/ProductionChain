#!/bin/bash

# Caminho base
SRC_DIR="src"
OUT_DIR="out"

# Limpa build anterior
echo "🧹 Limpando build anterior..."
rm -rf $OUT_DIR
mkdir -p $OUT_DIR

# Encontra todos os arquivos .java
echo "🔍 Encontrando arquivos Java..."
find $SRC_DIR -name "*.java" > sources.txt

# Compila os arquivos
echo "⚙️ Compilando..."
javac -d $OUT_DIR @sources.txt

# Verifica se compilou corretamente
if [ $? -ne 0 ]; then
  echo "❌ Erro na compilação."
  exit 1
fi

# Executa o programa principal
echo "🚀 Executando..."

# Altere 'cadeia.Main' se o seu Main.java não estiver em um package
java -cp $OUT_DIR cadeia.Main

# Opcional: limpa o arquivo temporário
rm sources.txt
