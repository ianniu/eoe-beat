import Request from './_request'
import { EOEBEAT_HOST } from '../utils/config'

const MusicService = {
  /**
   * get all music
   * @param {object} pageable { page: number, size: number }
   */
  async searchAllMusic(pageable) {
    const url = `${EOEBEAT_HOST}/music/search`
    const params = { condition: [], pageable }
    const res = await Request.post(url, params)
    return res.data
  },
  /**
   * users search music
   * @param {string} value
   * @param {object} pageable { page: number, size: number }
   */
  async searchMusic(value, pageable) {
    const url = `${EOEBEAT_HOST}/music/search`
    const params = {
      condition: [
        {
          name: 'userInput',
          value
        }
      ],
      pageable
    }
    const res = await Request.post(url, params)
    return res.data
  },
  /**
   * Get all songs from a playlist
   * @param {string} playlistId
   * @param {object} pageable { page: number, size: number }
   */
  async searchMusicInPlaylist(playlistId, pageable) {}
}

export default MusicService
