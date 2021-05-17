const Lab = require('lab')
const Code = require('code')
const lab = exports.lab = Lab.script()
const murmur2Partitioner = require('./index')

// shortcuts
const describe = lab.experiment
const it = lab.test
const expect = Code.expect

// key: partition count: partition number (result)
const javaPartitionerPreComputedResults = {
  'wu': {10: 0, 11: 7, 12: 4, 13: 3, 14: 6, 15: 10},
  'defgh': {10: 8, 11: 4, 12: 6, 13: 1, 14: 12, 15: 3},
  'thing': {10: 4, 11: 0, 12: 4, 13: 11, 14: 0, 15: 4},
  'thingäöüÄÖÜ': {10: 2, 11: 1, 12: 10, 13: 12, 14: 12, 15: 7},
  'äöüÄÖÜ': {10: 2, 11: 3, 12: 8}
}

describe('murmur2 partitioner', () => {
  describe('_toPositive method', () => {
    it('should multiply any given number bit-wise with 0x7fffffff', done => {
      try {
        expect(murmur2Partitioner._toPositive(23)).to.equal(23)
        expect(murmur2Partitioner._toPositive(-23)).to.equal(2147483625)
        expect(murmur2Partitioner._toPositive(Number.MAX_VALUE)).to.equal(0)
        expect(murmur2Partitioner._toPositive(Number.MIN_VALUE)).to.equal(0)
      } catch (e) {
        done(e)
      }

      done()
    })
  })

  describe('partition method', () => {
    it('should return the same partition number Kafka\'s Java client would', done => {
      try {
        Object.keys(javaPartitionerPreComputedResults).forEach(key => {
          Object.keys(javaPartitionerPreComputedResults[key]).forEach(partitionCount => {
            const partition = murmur2Partitioner.partition(key, partitionCount)
            try {
              expect(partition).to.equal(javaPartitionerPreComputedResults[key][partitionCount])
            } catch (e) {
              console.error(`Failed at key ${key} and partition count ${partitionCount}, should be ${javaPartitionerPreComputedResults[key][partitionCount]}, was ${partition}`)
              throw e
            }
          })
        })
      } catch (e) {
        return done(e)
      }

      done()
    })
  })
})

