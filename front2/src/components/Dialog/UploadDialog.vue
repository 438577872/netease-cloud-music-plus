<template>
  <el-dialog :visible="visible.upload">
    <!--    <el-upload></el-upload>-->
    <template slot="title">
      上传歌曲
    </template>
    <template>
      <el-form label-width="100px">
        <el-form-item label="歌曲名称">
          <el-input v-model="form.musicName"/>
        </el-form-item>
        <el-form-item label="歌手姓名">
          <el-input v-model="form.author"/>
        </el-form-item>
        <!--        <el-form-item><el-input v-model="form.name"/></el-form-item>-->
        <!--        <el-form-item><el-input v-model="form.name"/></el-form-item>-->
        <!--        <el-form-item><el-input v-model="form.name"/></el-form-item>-->
        <el-form-item label="上传封面">
          <input type="file" @change="handleChangeImg"></input>
        </el-form-item>
        <el-form-item label="上传歌曲">
          <!--          <el-button>-->
          <input style="opacity: 1" @change="handleChangeMusic" type="file"/>
          <!--            <div style="position: absolute">-->
          <!--              {{form.musicName}}-->
          <!--            </div>-->
          <!--          </el-button>-->
        </el-form-item>
        <el-form-item label="">
          <el-button  type="primary" @click="handleCommit">
            上传
          </el-button>
        </el-form-item>
      </el-form>
    </template>


    <template slot="footer">
      <el-button type="danger" @click="visible.upload = false">
        关闭
      </el-button>
    </template>
  </el-dialog>
</template>

<script>
  import { uploadMusic } from '../../api/music'

  export default {
    name: 'UploadDialog',
    props: ['visible'],
    data() {
      return {
        form: {
          musicName: '',
          author: '',
          musicFile: null,
          imgFile: null

        }
      }
    },
    methods: {
      handleChangeImg(e) {
        // alert(123)
        // console.log(e.target.files[0].name)
        this.form.imgFile = e.target.files[0]

        // console.log(e)
      },
      handleChangeMusic(e) {
        this.form.musicFile = e.target.files[0]
        // this.form.musicName = musicName
      },
      handleCommit() {
        let fd = new FormData()
        for (let key in this.form) {
          fd.append(key, this.form[key])
        }
        uploadMusic(fd).then(resp=>{
          console.log(resp)

        })

        // fd.append('')
      }
    }
  }
</script>

<style scoped>

</style>
