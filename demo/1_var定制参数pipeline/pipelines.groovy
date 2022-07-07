//  var/pipelines.groovy
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    properties(
        [
            parameters(
                [
                gitParameter(
                    branchFilter: 'origin/(.*)',
                    defaultValue: 'master',
                    name: 'DEPLOY_BRANCH',
                    type: 'PT_BRANCH',
                    selectedValue: 'TOP',
                    sortMode: 'DESCENDING_SMART',
                    useRepository: '${config.gitrepo}'
                    )
                ]
            )
        ]
    )
    pipeline {
    agent none
        gitParameter(
            branchFilter: 'origin/(.*)',
            defaultValue: 'master',
            name: 'DEPLOY_BRANCH',
            type: 'PT_BRANCH',
            selectedValue: 'TOP',
            sortMode: 'DESCENDING_SMART',
            useRepository: 'git@git.taovip.com:yunpian-attila/yunpian-attila-userinfo.git'
        )
    stages {
        stage ('Example') {
            steps {
                // log.info 'Starting' 
                script { 
                    echo "hello ${config.name}"
                }
            }
        }
    }
}
}