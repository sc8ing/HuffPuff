# Huff Puff
Basic file compressor/decompressor using the Huffman algorithm. Done with a partner for CS II class.

## Usage:

1. Compile java files.
2. Run `java Huff [file to compress]`. Outputs a file with the same prefix + a .zip suffix in the same directory as the input.
3. Run `java Puff [compressed/huffed file]` to decompress. Will output in the same way as compression but as a .txt file.

***Note:*** This code takes zero safety precautions and will simply overwrite anything named the same as the output.