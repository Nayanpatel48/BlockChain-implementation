import hashlib

def hashGenerator(data):
    result = hashlib.sha256(data.encode())
    return result.hexdigest()

class Block:
    def __init__(self, data, hash, prev_hash):
        self.data = data
        self.hash = hash
        self.prev_hash = prev_hash

class Blockchain:
    def __init__(self):
        hashLast = hashGenerator('gen_last')
        hashStar = hashGenerator('gen_hash')

        genesis = Block('gen_data', hashStar, hashLast)
        self.chain = [genesis]

    def add_block(self, data):
        prev_hash=self.chain[-1].hash
        hash = hashGenerator(data+prev_hash)
        block = Block(data, hash, prev_hash)
        self.chain.append(block)
    
bc = Blockchain()
bc.add_block('1')
bc.add_block('2')
bc.add_block('3')

for block in bc.chain:
    print("Data: %s, Hash: %s, Prev Hash: %s" % (block.data, block.hash, block.prev_hash))